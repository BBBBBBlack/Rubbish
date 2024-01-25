## java代码规范

[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

### 格式化

* 括弧的使用——在写`if`,`for`,`while`语句时，不论块内有几条语句，均加上括弧
* 左大括号前无换行符，后有换行符
* 右大括号前有换行符，后无换行符（除非该大括号终止语句、方法、构造函数、命名类的主体）
* 空块可用`{}`
* 新块缩进两个空格
* 列限制100个字符
* 换行在更高的语法级别中断，缩进延续线至少+4个空格
* 每个变量声明只声明一个变量
* 局部变量在需要时声明，而非在块的开头声明

### 命名规范

* 所有标识符仅用字母和数字，不用特殊前后缀

* 软件包名：仅用小写字母和数字，无下划线

* 类名：名词或非名词短语

* 方法名、参数名、局部变量名：驼峰命名法

* 常量名：全部使用大写字母单词之间用下划线分隔

* JavaDoc

  * 基本格式如下

    ```java
    /**
     * Multiple lines of Javadoc text are written here,
     * wrapped normally...
     */
    public int method(String p1) { ... }
    ```

  * 块标签按`@param，@return，@throws，@deprecated`顺序出现，且不出现空的描述

### 注释

* 块注释与周围代码的缩进在相同级别
* 数字后缀用大写字母

### 编程实践

* 实现接口的方法，始终使用@Override注解
* 不忽视被捕获的Exception