#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include <stdlib.h>
struct node {
    int address;
    int size;
    int state;//进程号
    struct node* next;
}typedef node;
node* freelist, * busylist, * templist;
void menu() {
    printf("输入操作：\n");
    printf("0. 初始化\n");
    printf("1. 新增空闲表\n");
    printf("2. 分配操作\n");
    printf("3. 回收操作\n");
}
void print_list(node* head,int type) {
    node* cur = head;
    while (cur->next != NULL) {
        cur = cur->next;
        if (type == 2) {
            printf("state:%d", cur->state);
        }
        printf("address:%d;size:%d\n", cur->address, cur->size);
    }
}
void add_node(node* head, node* new_node);
void delete_node(node* head);
void free_list(node* head);
int sortlist(int i);
void add_free_list(int num);
void add_temp_list(int num);
void fit(int type, long cnum, long csize);
int recover(long cnum);
int total_size;
int main() {
    freelist = (struct node*)malloc(sizeof(node));
    freelist->next = NULL;
    busylist = (struct node*)malloc(sizeof(node));
    busylist->next = NULL;
    templist = (struct node*)malloc(sizeof(node));
    templist->next = NULL;
    while (1) {
        menu();
        int type;
        scanf("%d", &type);
        while (type != 0 && total_size == 0) {
            printf("请先初始化\n");
            menu();
            scanf("%d", &type);
        }
        switch (type) {
        case 0: {
            printf("输入内存大小：\n");
            scanf("%d", &total_size);
            free_list(freelist);
            free_list(busylist);
            free_list(templist);
            break;
        }
        case 1: {
            int num;
            printf("输入添加个数：");
            scanf("%d", &num);
            add_free_list(num);
            print_list(freelist, 1);
            break;
        }
        case 2: {
            printf("输入选择的算法：\n");
            int type;
            printf("1. 最先适应算法\n2. 最佳适应算法\n3. 最坏适应算法\n4. 退出\n");
            scanf("%d", &type);
            if (type != 4) {
                int num;
                printf("输入添加个数：");
                scanf("%d", &num);
                add_temp_list(num);
                while (templist->next != NULL) {
                    node* cur = templist->next;
                    fit(type, cur->state, cur->size);
                    delete_node(templist);
                }
            }
            break;
        }
        case 3: {
            printf("输入回收的进程号：\n");
            int type;
            scanf("%d", &type);
            recover(type);
            break;
        }
        }
    }
    return 0;
}
void add_node(node* head,node* new_node) {
    new_node->next = head->next;
    head->next = new_node;
}
void delete_node(node* head) {
    if (head->next != NULL) {
        node* cur = head->next;
        head->next = cur->next;
        free(cur);
    }
}
void add_free_list(int num) {
    for (int i = 0; i < num; i++) {
        int type2;
        node* new_node = (node*)malloc(sizeof(node));
        printf("from:\n");
        scanf("%d", &type2);
        new_node->address = type2;
        printf("size:\n");
        scanf("%d", &type2);
        new_node->size = type2;
        add_node(freelist, new_node);
    }
}
void add_temp_list(int num) {
    for (int i = 0; i < num; i++) {
        int type2;
        node* new_node = (node*)malloc(sizeof(node));
        printf("state:\n");
        scanf("%d", &type2);
        new_node->state = type2;
        printf("from:\n");
        scanf("%d", &type2);
        new_node->address = type2;
        printf("size:\n");
        scanf("%d", &type2);
        new_node->size = type2;
        add_node(templist, new_node);
    }
}
void free_list(node* head) {
    node* cur = head;
    while (cur->next != NULL) {
        node* nn = cur->next;
        cur->next = nn->next;
        free(nn);
    }
}
int sortlist(int i) { 
    if (freelist == NULL || freelist->next == NULL) return 0;
    node* change, * last;
    node* newhead, * newlast = NULL;
    newhead = (node*)malloc(sizeof(node));
    newhead->next = NULL;
    while (freelist->next != NULL) {
        change = freelist;
        last = freelist->next;
        if (last != NULL)//说明至少有一个,last->next才有意义 
        {
            while (last->next != NULL) {
                //按照起始地址递增
                if (i == 1) {
                    if (change->next->address < last->next->address) change = last;
                }
                //按照大小递增
                else if (i == 2) {
                    if (change->next->size < last->next->size) change = last;
                }
                //按照大小递减
                else if (i == 3) {
                    if (change->next->size > last->next->size) change = last;
                }
                last = last->next;
            }
        }
        last = change->next;
        change->next = last->next;
        last->next = newhead->next;
        newhead->next = last;
    }
    free(freelist);
    freelist = newhead;
    newhead = newlast = last = change = NULL;
    return 0;
}
//type=1:FF;type=2:BF;type=3:WF 
void fit(int type,long cnum, long csize){
    sortlist(type);
    node* head = freelist, * nn;
    while (head->next != NULL){
        if (head->next->size >= csize){
            if (head->next->size == csize){
                nn = head->next;
                nn->state = cnum;
                head->next = nn->next;
                nn->next = busylist->next;
                busylist->next = nn;
            }
            else{
                nn = (node*)malloc(sizeof(node));
                nn->address = head->next->address;
                nn->state = cnum;
                nn->size = csize;
                head->next->size -= csize;
                head->next->address += csize;
                nn->next = busylist->next;
                busylist->next = nn;
            }
            csize = -1;
            break;
        }
        head = head->next;
    }
    if (csize == -1) 
        printf("分配成功\n");
    else { 
        printf("分配失败\n"); 
        return; 
    }
    print_list(freelist, 1);
    printf("-----------\n");
    print_list(busylist, 2);
    //showlist(1); 
    //showlist(2);
    return;
}
int recover(long cnum)//回收功能 
{
    node* busyhead = busylist, * freehead = freelist, * nn;
    if (busyhead->next == NULL){
        printf("无可回收资源\n");
        return 0;
    }
    while (busyhead->next != NULL){
        if (busyhead->next->state == cnum)
        {
            nn = busyhead->next;
            busyhead->next = nn->next;
            nn->next = freehead->next;
            freehead->next = nn;
        }
        else busyhead = busyhead->next;
    }
    sortlist(1);//1 按照起始地址递增空闲区 
    freehead = freelist;
    freehead = freehead->next;
    if (freehead == NULL) {
        printf("无空闲资源\n");
        return 0;
    }
    while (freehead->next != NULL)
    {
        if ((freehead->size + freehead->address) == freehead->next->address)//合并 
        {
            nn = freehead->next;
            freehead->size = freehead->size + nn->size;
            freehead->next = nn->next;
            nn->next = NULL;
            free(nn);
        }
        else freehead = freehead->next;
    }
    print_list(freelist, 1);
    printf("-----------\n");
    print_list(busylist, 2);
    return 0;
}
