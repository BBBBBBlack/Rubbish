#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<iostream>
using namespace std;
struct node {
	int no; //页号
	int A;
	int M;
	node* next;
};
int total;
node* list;

void print_list() {
	node* cur = list;
	if (cur != NULL) {
		do {
			printf("页号 %d\tA %d\tM %d\t\n", cur->no, cur->A, cur->M);
			cur = cur->next;
		} while (cur != list);
	}
}

void insert(int no) {
	node* tail;
	tail = list;
	if (tail == NULL) {
		list = tail = (node*)malloc(sizeof(node));
		list->next = list;
		list->no = no;
		list->A = 1;
		list->M = 0;
	}
	else {
		while (tail->next != list) {
			tail = tail->next;
		}
		node* p = (node*)malloc(sizeof(node));
		p->next = tail->next;
		tail->next = p;
		p->no = no;
		p->A = 1;
		p->M = 0;
	}
}
void init() {
	printf("请输入内存块数：\n");
	scanf("%d", &total);
}

node* find(int no) {
	node* tempb;
	tempb = list;
	if (list != NULL) {
		do {
			if (tempb->no == no)
				return tempb;
			tempb = tempb->next;
		} while (tempb != list);
	}
	return NULL;
}

node* find00(int no){
	node* tempb;
	tempb = list;
	if (list != NULL) {
		do{
			if (tempb->A == 0 && tempb->M == 0)
				return tempb;
			tempb = tempb->next;
		} while (tempb != list);
	}
	return NULL;
}
node* find01(int no) {
	node* tempb;
	tempb = list;
	if (list != NULL) {
		do {
			if (tempb->A == 0 && tempb->M == 1)
				return tempb;
			tempb->A = 0;
			tempb = tempb->next;
		} while (tempb != list);
	}
	return NULL;
}

void replace(node* cur, int no) {
	cur->no = no;
	cur->A = 1;
	cur->M = 0;
	print_list();
}
int main() {
	init();
	int n = 0;
	while (1) {

		if (n < total) {
			printf("输入要访问的页面号（-1退出）\n");
			int num;
			scanf("%d", &num);
			if (num == -1) {
				break;
			}
			insert(num);
			n++;
			print_list();
		}
		else {
			printf("访问or修改？\n1. 修改\t2.访问\t3. 退出\n");
			int type;
			scanf("%d", &type);
			int page;
			if (type == 1) {
				printf("输入修改页号：\n");
				scanf("%d", &page);
				node* cur;
				if (!(cur = find(page))) {
					printf("无指定页\n");
					continue;
				}
				cur->M = 1;
				print_list();
			}
			else if (type == 2) {
				printf("输入访问页号：\n");
				scanf("%d", &page);
				node* cur;
				while (1) {
					//内存中找到该块
					if ((cur = find(page))) {
						printf("find0:\n");
						replace(cur, page);
						break;
					}
					//找A = 0 && M = 0
					else if ((cur = find00(page))) {
						printf("find1:\n");
						replace(cur, page);
						break;
					}
					//找A = 0 && M = 1
					else if ((cur = find01(page))) {
						printf("find2:\n");
						replace(cur, page);
						break;
					}
				}
			}
			else {
				break;
			}
		}
	}
	return 0;
}