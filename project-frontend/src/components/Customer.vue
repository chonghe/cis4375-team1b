
<template>
  <div>
    <el-table
      stripe
      :data="customerForm"
      style="width: 100%"
    >
      <el-table-column type="index" />
      <el-table-column prop="customer_firstname" label="Customer First Name" width="180"> </el-table-column>
      <el-table-column prop="customer_lastname" label="Customer Last Name" width="180"> </el-table-column>
      <el-table-column prop="phone_number" label="Phone Number"> </el-table-column>
      <el-table-column prop="member_status" label="Membership Status"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data(){
    return{
      customerForm: [{
        customer_firstname: "",
        customer_lastname: "",
        phone_number: "",
        member_status: "",
      }],
    }
  },
  methods:{
    async getAllCustomer(){
      const {data:res} = await this.$http.get("allCustomer");
      //console.log(res);
      this.customerForm=res.data
    },
  },
  created(){
    this.getAllCustomer()
  }
};
</script>

<style scoped>

</style>
