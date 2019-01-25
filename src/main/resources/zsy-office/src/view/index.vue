<template>
  <div>
      huangxiao
  </div>
</template>

<script>
  export default {
    name: 'Index',
    data() {
      return {
        formData: {
          phone: '',
          password: ''
        }
      };
    },
    methods: {
      //登录
      login() {
        console.log(1)
      }
    },
    mounted() {
      this.login();
    }
  };
</script>

<style scoped>



</style>
