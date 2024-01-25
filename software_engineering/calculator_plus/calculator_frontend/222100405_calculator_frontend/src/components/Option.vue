<template>
  <div>
    <!-- <label>{{ label }}</label><br> -->
    <select id="id" class="select-box" v-model="selectedOption">
      <option :value=null disabled>{{ optionType }}</option>
      <option v-for="(option, index) in options" :key="index" :value="index">{{ option }}</option>
    </select>
  </div>
</template>
  
<script>
export default {
  props: {
    optionType: {
      type: String,
      required: true
    },
    options: {
      type: Array,
      required: true
    },
    value: {
      type: Number,
      default: null
    },
    // label: {
    //   type: String,
    //   required: false
    // }
  },
  data() {
    return {
      selectedOption: this.value
    }
  },
  watch: {
    selectedOption(newValue) {
      this.$emit('input', newValue)
    }
  }
}
</script>
<style>
.select-box {
  appearance: none;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 8px;
  font-size: 16px;
  color: #333;
  z-index: 0;
}

.select-box:focus {
  outline: none;
  /* border-color: #0078d7; */
  box-shadow: 0 0 0 2px rgba(0, 120, 215, 0.3);
}
</style>