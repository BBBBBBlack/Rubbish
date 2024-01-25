<template>
    <div class="input-wrapper">
        <!-- <label>{{ label }}</label> -->
        <input :id="id" :type="type" v-model="inputMoney" :placeholder="placeholder" />
    </div>
</template>
  
<script>
export default {
    props: {
        id: {
            type: String,
            required: false
        },
        type: {
            type: String,
            default: 'text'
        },
        value: {
            type: String,
            default: null
        },
        placeholder: {
            type: String,
            default: ''
        }
    },
    data() {
        return {
            inputMoney: this.value
        }
    },
    methods: {
    },
    watch: {
        inputMoney(newValue) {
            this.$emit('input', newValue)
        }
    }
}
</script>
<style>
.input-wrapper {
    display: flex;
    flex-direction: column;
    /* margin-bottom: 20px; */
}

label {
    /* margin-bottom: 8px; */
    font-size: 16px;
    color: #333;
}

input {
    width: 130px;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: #fff;
    font-size: 16px;
    color: #333;
}

input:focus {
    outline: none;
    border-color: #0078d7;
    box-shadow: 0 0 0 2px rgba(0, 120, 215, 0.3);
}
</style>