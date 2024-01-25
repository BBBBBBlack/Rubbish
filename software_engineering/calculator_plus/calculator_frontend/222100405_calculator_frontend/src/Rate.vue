<template>
  <div>
    <!-- 切换时将setNull置1，隐藏该页面所有元素 -->
    <router-link id="rlink" to="/app"><button @click="setNull = true">基础计算器</button></router-link>
    <!-- 新界面在此处加载 -->
    <router-view></router-view>
    <div class="total" v-if="!setNull">
      <div class="center">
        <Option optionType="利率类型" :options="options1" v-model="rateType" />
      </div>
      <br>
      <div class="container">
        <div id="selects" class="column left">
          <div v-if="rateType === 0">
            <label>存款金额：</label>
            <Input placeholder="请输入存款金额" v-model="money" type="number" />
            <label>存款时长：</label>
            <Option optionType="存款时长" :options="options2" v-model="storeTime" />
          </div>
          <div v-if="rateType === 1">
            <label>贷款金额：</label>
            <Input placeholder="请输入贷款金额" v-model="money" type="number" />
            <label>贷款时长：</label>
            <Option optionType="贷款时长" :options="options3" v-model="storeTime" />
          </div>
          <div v-if="rateType != null">
            <div>
              <label class="inline-label"> 利息：</label>
              <div id="inter" class="inline-div">{{ interest }}</div>
            </div>
            <button id="calRate" @click="getRate">计算</button>
          </div>
        </div>
        <div class="separator"></div>
        <div id="update" class="column right">
          <div>
            <button @click="getRateTable">利率查询</button>
          </div>
          <div>
            <table>
              <thead>
                <div v-if="rateType === 0 && rateTable != null">
                  <tr>
                    <th>存款利率表</th>
                  </tr>
                </div>
                <div v-if="rateType === 1 && rateTable != null">
                  <tr>
                    <th>贷款利率表</th>
                  </tr>
                </div>
              </thead>
              <tbody>
                <tr v-for="(rate, key) in rateTable" :key="key">
                  <td>{{ rateMap[key] }}</td>
                  <Input type="number" v-model="rateTable[key]" />
                </tr>
              </tbody>
            </table>
            <div v-if="rateTable != null">
              <button @click="updateRate">修改利率</button>
            </div>
          </div>
        </div>
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
  name: 'Rate',
  data: function () {
    return {
      setNull: false,
      rateType: null,
      storeTime: null,
      money: null,
      interest: null,
      rateTable: null,
      rateMap: {
        "demandDep": "活期存款", "threeMon": "三个月", "sixMon": "六个月",
        "oneYear": "一年", "twoYear": "二年", "threeYear": "三年", "oneToThree": "一至三年",
        "threeToFive": "三至五年", "fiveYear": "五年"
      },
      options1: ['存款', '贷款'],
      options2: ['三个月', '半年', '1年', '2年', '3年', '5年'],
      options3: ['半年', '1年', '2年', '3年', '4年', '5年'],
    };
  },
  components: {
    Button,
    Option,
    Input
  },
  methods: {
    async getRate() {
      const resp = await axios.get('/api/getRate', {
        params: {
          rateType: this.rateType,
          storeTime: this.storeTime,
          money: this.money
        }
      })
        .then(response => {
          let rate = response.data
          let time
          if (this.rateType === 0 && this.storeTime === 0) {
            time = 0.25;
          } else if (this.rateType === 0 && this.storeTime === 1) {
            time = 0.5;
          } else if (this.rateType === 1 && this.storeTime === 0) {
            time = 0.5;
          } else {
            time = this.storeTime
          }
          this.interest = rate * this.money * time
        })
        .catch(error => {
          console.error(error)
        })
    },
    async getRateTable() {
      const resp = await axios.get('/api/getRateTable', {
        params: {
          rateType: this.rateType,
        }
      })
        .then(response => {
          this.rateTable = response.data.data
          console.log(this.rateTable)
        })
        .catch(error => {
          console.error(error)
        })
    },
    async updateRate() {
      const data = {
        rateTable: this.rateTable,
        rateType: this.rateType
      };
      const json = JSON.stringify(data);
      await axios.post('/api/updateRateTable', json, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (response.data.code == 200) {
            this.rateTable = response.data.data
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
}
</script>
<style>
.total {
  border-radius: 20px;
  background-color: rgba(226, 234, 107, 0.442);
  width: 600px;
  height: 440px;
  margin: 20px auto;
  border: 2px solid #ccc;
  /* 添加边框 */
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* text-align: center; */
}

.container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 0 35px;
}

.left :not(:first-child) {
  /* margin-right: auto; */
  /* margin-left: 5px; */
  margin-bottom: 30px;
}

#calRate {
  position: absolute;
  bottom: 20px;
  /* 距离底部 20 像素 */
  right: 330px;
  /* 距离右侧 20 像素*/

}

.right {
  line-height: 1.5;
}

.separator {
  position: absolute;
  left: 50%;
  top: 54%;
  transform: translate(-50%, -50%);
  width: 2px;
  height: 320px;
  background-color: #ccc;
}

.inline-label {
  display: inline-block;
}

.inline-div {
  display: inline-block;
}

.center {
  text-align: center;
}

.total button {
  background-color: lightskyblue;
  border: none;
  border-radius: 4px;
  color: #fff;
  padding: 6px 16px;
  font-size: 15px;
  cursor: pointer;
}

.total button:hover {
  background-color: #207dc4;
}
#rlink {
  position: absolute;
  top: 300px;
  right: 300px;
  width: 100px;
  height: 30px;
}
</style>