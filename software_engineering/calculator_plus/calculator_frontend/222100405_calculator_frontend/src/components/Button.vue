<template>
  <div class="button" @click="handleButtonClick">
    <slot></slot>
  </div>
</template>

<script>
export default {
  methods: {
    handleButtonClick() {
      this.$emit("click", this.$el.id, this.$el.innerText);
    }
  }
};
</script>

<style scoped>
.button {
  display: grid;
  justify-content: center;
  align-items: center;
  float: left;
  background-color:whitesmoke;
  border: none;
  border-radius: 4px;
  color: black;
  padding: 6px 16px;
  font-size: 15px;
  cursor: pointer;
  margin-bottom: -30px;
  margin-left: 6px; /* 设置左边距为 10 像素 */
  margin-right: 6px; /* 设置右边距为 10 像素 */
  margin-top: -1px; /* 设置右边距为 10 像素 */
  height: 20px;
  width: 11px;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* 添加阴影 */
}

.button:hover {
  background-color: cadetblue;
}
</style>
