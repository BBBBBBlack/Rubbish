<template>
  <div>
    <router-link id="rlink" to="/rate"><button @click="setNull = true">利率计算器</button></router-link>
    <router-view></router-view>
    <div v-if="!setNull">
      <div id="block">
        <div id="block00"></div>
        <div id="block01"></div>
        <div id="block1">{{ expression }}</div>
        <div id="block2">{{ result }}</div>
      </div>
      <div class="bangbang">
        <p>
          <Button id="(" @click="handleButtonClick">(</Button>
          <Button id="C" @click="handleButtonClick">C</Button>
          <Button id="/" @click="handleButtonClick">/</Button>
          <Button id="*" @click="handleButtonClick">*</Button>
          <Button id="d" @click="handleButtonClick">del</Button>
        </p>
        <p>
          <Button type="button" id=")" @click="handleButtonClick">)</Button>
          <Button type="button" id="n" @click="handleButtonClick">ln</Button>
          <Button type="button" id="g" @click="handleButtonClick">log</Button>
          <Button type="button" id="t" @click="handleButtonClick">√</Button>
          <Button type="button" id="-" @click="handleButtonClick">-</Button>
        </p>
        <p>
          <Button type="button" id="sin" @click="handleButtonClick">sin</Button>
          <Button type="button" id="7" @click="handleButtonClick">7</Button>
          <Button type="button" id="8" @click="handleButtonClick">8</Button>
          <Button type="button" id="9" @click="handleButtonClick">9</Button>
          <Button type="button" id="+" @click="handleButtonClick">+</Button>
        </p>
        <p>
          <Button type="button" id="cos" @click="handleButtonClick">cos</Button>
          <Button type="button" id="4" @click="handleButtonClick">4</Button>
          <Button type="button" id="5" @click="handleButtonClick">5</Button>
          <Button type="button" id="6" @click="handleButtonClick">6</Button>
          <Button type="button" id="^" @click="handleButtonClick">^</Button>
        </p>
        <p>
          <Button type="button" id="tan" @click="handleButtonClick">tan</Button>
          <Button type="button" id="1" @click="handleButtonClick">1</Button>
          <Button type="button" id="2" @click="handleButtonClick">2</Button>
          <Button type="button" id="3" @click="handleButtonClick">3</Button>
          <Button type="button" id="=" class="equals" @click="handleButtonClick">=</Button>
        </p>
        <p>
          <Button type="button" id="ans" @click="getHistory">ans</Button>
          <Button type="button" id="%" @click="handleButtonClick">%</Button>
          <Button type="button" id="0" @click="handleButtonClick">0</Button>
          <Button type="button" id="." @click="handleButtonClick">.</Button>
        </p>
      </div>
    </div>
  </div>
</template>
<script>
import Button from '@/components/Button.vue'
import Option from '@/components/Option.vue'
import Input from '@/components/Input.vue'
import axios from 'axios'
export default {
  name: 'App',
  data: function () {
    return {
      setNull: false,
      rateType: null,
      storeTime: null,
      money: null,
      interest: null,
      rateTable: [],
      options1: ['存款', '贷款'],
      options2: ['三个月', '半年', '1年', '2年', '3年', '5年'],
      options3: ['半年', '1年', '2年', '3年', '4年', '5年'],
      //计算利率
      //计算表达式
      expression: '',
      result: '',
      res: '',
      data_stack: [],
      opa_stack: []
    };
  },
  components: {
    Button,
    Option,
    Input
  },
  methods: {
    handleButtonClick(id, innerText) {
      const buttonId = id;
      const buttonValue = innerText;
      // 在这里写点击事件的代码
      if (buttonId === "C") {
        this.clear();
        this.expression = this.result = this.res = '';

      } else if (buttonId === "d") {
        this.expression = this.expression.slice(0, -1);
      }
      else if (buttonId === "=") {
        this.res = this.transStr(this.expression);
        //alert(this.res);
        try {
          this.result = this.getRes(this.res);
        } catch (e) {
          alert(e);
          this.clear();
          this.expression = "";
        }
      }
      else {
        this.expression += buttonValue;
      }
    },
    getRes() {
      for (let i = 0; i < this.res.length; i++) {
        switch (this.res[i]) {
          case "{":
          case "[":
          case "<":
          case "(":
          case "a":
          case "h":
          case "e":
            this.opa_stack.push(this.res[i]);
            break;
          case "}":
            let t6;
            while (this.opa_stack.length > 0 && (t6 = this.opa_stack.pop()) !== "{") {
              this.opa_stack.push(t6);
              this.popCalculator();
            }
            let temp1 = this.data_stack.pop();
            this.data_stack.push(Math.sin(temp1));
            break;
          case "]":
            let t7;
            while (this.opa_stack.length > 0 && (t7 = this.opa_stack.pop()) !== "[") {
              this.opa_stack.push(t7);
              this.popCalculator();
            }
            let temp2 = this.data_stack.pop();
            this.data_stack.push(Math.cos(temp2));
            break;
          case ">":
            let t8;
            while (this.opa_stack.length > 0 && (t8 = this.opa_stack.pop()) !== "<") {
              this.opa_stack.push(t8);
              this.popCalculator();
            }
            let temp3 = this.data_stack.pop();
            this.data_stack.push(Math.tan(temp3));
            break;
          case "b":
            let t9;
            while (this.opa_stack.length > 0 && (t9 = this.opa_stack.pop()) !== "a") {
              this.opa_stack.push(t9);
              this.popCalculator();
            }
            let temp4 = this.data_stack.pop();
            this.data_stack.push(Math.log10(temp4));
            break;
          case "d":
            let t10;
            while (this.opa_stack.length > 0 && (t10 = this.opa_stack.pop()) !== "h") {
              this.opa_stack.push(t10);
              this.popCalculator();
            }
            let temp5 = this.data_stack.pop();
            this.data_stack.push(Math.log(temp5));
            break;
          case "f":
            let t11;
            while (this.opa_stack.length > 0 && (t11 = this.opa_stack.pop()) !== "e") {
              this.opa_stack.push(t11);
              this.popCalculator();
            }
            let temp6 = this.data_stack.pop();
            this.data_stack.push(Math.sqrt(temp6));
            break;
          case ")":
            let t1;
            while (this.opa_stack.length > 0 && (t1 = this.opa_stack.pop()) !== "(") {
              this.opa_stack.push(t1);
              this.popCalculator();
            }
            break;
          case "+":
          case "-":
            if (i === 0 ||
              (this.res[i] === "-" && isNaN(this.res[i - 1]) &&
                this.res[i - 1] !== ")" && this.res[i - 1] !== "}" && this.res[i - 1] !== "]" && this.res[i - 1] !== ">") &&
              this.res[i - 1] !== "b" && this.res[i - 1] !== "d" && this.res[i - 1] !== "f") {
              let [num, size] = this.getNum(this.res, i);
              this.data_stack.push(num);
              i += size - 1;
            } else {
              while (this.opa_stack.length > 0) {
                let t3 = this.opa_stack.pop();
                this.opa_stack.push(t3);
                if (t3 !== "(" && t3 !== "{" && t3 !== "[" && t3 !== "<" &&
                  t3 !== "a" && t3 !== "h" && t3 !== "e") {
                  this.popCalculator();
                } else {
                  break;
                }
              }
              this.opa_stack.push(this.res[i]);
            }
            break;
          case "*":
          case "/":
          case "%":
            while (this.opa_stack.length > 0) {
              let t2 = this.opa_stack.pop();
              this.opa_stack.push(t2);
              if (t2 === "*" || t2 === "/" || t2 === "%" || t2 === "^") {
                this.popCalculator();
              } else {
                break;
              }
            }
            this.opa_stack.push(this.res[i]);
            break;
          case "^":
            this.opa_stack.push(this.res[i]);
            break;
          default:
            if (!isNaN(this.res[i])) {
              let [num, size] = this.getNum(this.res, i);
              this.data_stack.push(num);
              i += size - 1;
            } else {
              throw "expression error";
            }
            break;
        }
      }
      while (this.data_stack.length >= 2 && this.opa_stack.length >= 1) {
        this.popCalculator();
      }
      if (this.data_stack.length === 1 && this.opa_stack.length === 0) {
        this.result = this.data_stack.pop();
        //提交记录
        let str = this.expression + "=" + this.result;
        this.CommitHis(str);
        return this.result;
      }
      throw "expression error";
    },
    getNum(str, index) {
      let i;
      for (i = index; i < str.length; i++) {
        if (isNaN(str[i])) {
          if (str[i] !== "." && !((str[i] === "-" || str[i] === "+") && i === index)) {
            break;
          }
        }
      }
      return [parseFloat(str.slice(index, i + 1)), i - index];
    },
    popCalculator() {
      if (this.data_stack.length >= 2 && this.opa_stack.length >= 1) {
        let data2 = this.data_stack.pop();
        let data1 = this.data_stack.pop();
        let opa = this.opa_stack.pop();
        switch (opa) {
          case "+":
            this.data_stack.push(this.accAdd(data1, data2));
            break;
          case "-":
            this.data_stack.push(this.accAdd(data1, -data2));
            break;
          case "*":
            this.data_stack.push(this.accMul(data1, data2));
            break;
          case "/":
            if (data2 !== 0) {
              this.data_stack.push(this.accDiv(data1, data2));
              break;
            } else {
              throw new Error("divide by zero");
            }
          case "%":
            this.data_stack.push(data1 % data2);
            break;
          case "^":
            this.data_stack.push(Math.pow(data1, data2))
            break;
          default:
            throw "expression error";
        }
        return true;
      }
      throw "expression error";
    },
    clear() {
      this.data_stack.length = 0;
      this.opa_stack.length = 0;
      this.res = "";
    },
    transStr(str) {
      let newStr = str.replace(/sin/g, "s").replace(/cos/g, "c").replace(/tan/g, "t")
        .replace(/ln/g, "n").replace(/log/g, "g").replace(/√/g, "r");
      for (let i = 0; i < newStr.length; i++) {
        if (newStr[i] === "s") {
          newStr = this.replaceX(newStr, i, "{", "}");
        } else if (newStr[i] === "c") {
          newStr = this.replaceX(newStr, i, "[", "]");

        } else if (newStr[i] === "t") {
          newStr = this.replaceX(newStr, i, "<", ">");
        } else if (newStr[i] === "n") {
          newStr = this.replaceX(newStr, i, "a", "b");
        } else if (newStr[i] === "g") {
          newStr = this.replaceX(newStr, i, "h", "d");
        } else if (newStr[i] === "r") {
          newStr = this.replaceX(newStr, i, "e", "f");
        }
      }
      return newStr;
    },
    //为所有的sin cos tan添加括号
    replaceX(str, index, ch1, ch2) {
      let stack = [];
      if (str[index + 1] != '(') {
        let [num, len] = this.getNum(str, index + 1);
        str = str.slice(0, index) + ch1 + str.slice(index + 1, index + 1 + len) + ch2 + str.slice(index + 1 + len);
      } else {
        for (let i = index; i < str.length; i++) {
          if (str[i] === "(") {
            if (stack.length === 0) {
              str = str.slice(0, i - 1) + ch1 + str.slice(i + 1);
            }
            stack.push(str[i]);
          } else if (str[i] === ")") {
            stack.pop();
            if (stack.length === 0) {
              str = str.slice(0, i) + ch2 + str.slice(i + 1);
              break;
            }
          }
        }
      }

      return str;
    },
    accDiv(arg1, arg2) {
      var t1 = 0, t2 = 0, r1, r2;
      try {
        t1 = arg1.toString().split(".")[1].length;
      }
      catch (e) {
        t1 = 0;
      }
      try {
        t2 = arg2.toString().split(".")[1].length;
      }
      catch (e) {
        t1 = 0;
      }
      r1 = Number(arg1.toString().replace(".", ""));
      r2 = Number(arg2.toString().replace(".", ""));
      return (r1 / r2) * Math.pow(10, t2 - t1);
    },
    accAdd(arg1, arg2) {
      var r1, r2, m, c;
      try {
        r1 = arg1.toString().split(".")[1].length;
      }
      catch (e) {
        r1 = 0;
      }
      try {
        r2 = arg2.toString().split(".")[1].length;
      }
      catch (e) {
        r2 = 0;
      }
      c = Math.abs(r1 - r2);
      m = Math.pow(10, Math.max(r1, r2));
      if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
          arg1 = Number(arg1.toString().replace(".", ""));
          arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
          arg1 = Number(arg1.toString().replace(".", "")) * cm;
          arg2 = Number(arg2.toString().replace(".", ""));
        }
      } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
      }
      return (arg1 + arg2) / m;
    },
    accMul(arg1, arg2) {
      var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
      try {
        m += s1.split(".")[1].length;
      } catch (e) {
        m += 0;
      }
      try {
        m += s2.split(".")[1].length;
      }
      catch (e) {
        m += 0;
      }
      return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
    },
    async CommitHis(str) {
      await axios.post('/api/addHistory', null,
        { params: { expression: str } })
        .then(response => {
          console.log(response.data)
        })
        .catch(error => {
          console.error(error)
        })
    },
    async getHistory() {
      await axios.get('/api/getHistory')
        .then(response => {
          let [first, ...rest] = response.data.data;
          this.expression = first.history;
          this.result = first.result;
          const message = rest.map(item => `history: ${item.history}\nresult: ${item.result}`).join('\n\n')
          alert(message)
        })
        .catch(error => {
          console.error(error)
        })
    }
  }
}
</script>

<style>
#d {
  color: red;
}

#C {
  color: black;
}



#block {
  font-size: 160%;
  font-family: "Calibri", sans-serif;
}

#block00 {
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
  background: gainsboro;
  height: 200px;
  width: 290px;
  position: absolute;
  top: 157px;
  left: 50%;
  transform: translate(-50%, 0);
  text-align: right;
  padding-right: 3px;
  padding-bottom: 6px;
  color: black;
}

#block1 {
  text-indent: 10px;
  font-size: 100%;
  font-family: "Calibri", sans-serif;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
  background-color: aliceblue;
  height: 70px;
  width: 270px;
  position: absolute;
  top: 163px;
  left: 50%;
  transform: translate(-50%, 0);
  color: black;
}

#block2 {
  font-size: 100%;
  font-family: "Calibri", sans-serif;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  background: aliceblue;
  height: 26px;
  width: 268px;
  position: absolute;
  top: 233px;
  left: 50%;
  transform: translate(-50%, 0);
  text-align: right;
  padding-right: 3px;
  padding-bottom: 6px;
  color: black;
}

.bangbang {
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  background: gainsboro;
  top: 410px;
  height: 345px;
  width: 290px;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -40%);
  display: grid;
  justify-content: center;
  grid-row-gap: -5000px;
}

/* .equals {
  z-index: 2;
  height: 400px;
  color: crimson;
} */

#get {
  height: 85px;
  width: 280px;
  font-size: 160%;
  font-family: "Calibri", sans-serif;
}

#rlink {
  position: absolute;
  top: 300px;
  right: 300px;
  width: 100px;
  height: 30px;
}
</style>
