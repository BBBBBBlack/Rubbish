## 前端代码规范

[Google HTML/CSS Style Guide](https://google.github.io/styleguide/htmlcssguide.html)

[Google JavaScript Style Guide](https://google.github.io/styleguide/jsguide.html)

### General

* 对嵌入式资源使用HTTPS协议

  ```html
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  ```

* 一次缩进两个空格

  ```html
  <ul>
    <li>Fantastic
    <li>Beast
  </ul>
  ```

* 全部使用小写字母

* 移除结尾的空白

* 编码采用UTF-8

### HTML/CSS规范

**HTML**

* 使用HTML5
* 使用有效的HTML代码
* 根据元素的作用和自身目的正确使用HTML元素
* 严格区分网页的结构（HTML语言）、呈现（CSS）和行为（JavaScript），并且尽量减少它们之间的交互
* 不要使用实体引用

**CSS**

* 使用有效的CSS代码

* 使用有意义且通用的类名

* 使用连字符`-`分隔一个类名中的单词

* 避免使用类型选择器

* 属性值尽量缩写

  ```css
  border-top: 0;
  font: 100%/1.6 palatino, georgia, serif;
  padding: 0 1em 2em;
  ```

* 省略`0`后的单位，省略`0`开头的小数小数点前的`0`，十六进制尽量使用3个字符

  

### JavaScript规范

* 使用`const let`声明局部变量，不要使用`var`
* 每次声明一个变量，且在有需要的时候声明，而非在块的开头
* 数组的每个元素在尾部加上逗号
* 不在数组中使用非数字类型的元素，而是使用Map或Object代替
* 普通字符串用单引号分隔，而非双引号；复杂文本使用模板文本串联
* 命名仅用字母和数字
* 类、接口使用大驼峰命名法；类名、枚举使用大驼峰命名法
* 常量全部大写，单词间以下划线分隔

