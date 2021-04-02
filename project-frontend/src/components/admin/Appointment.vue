<template>
  <div>
    <el-table
      stripe
      :data="customerForm"
      style="width: 100%"
    >
      <el-table-column type="index" />
      <el-table-column prop="customer_firstname" label="Customer Firstname" width="180"> </el-table-column>
      <el-table-column prop="customer_lastname" label="Customer Lastname" width="180"> </el-table-column>
      <el-table-column prop="service_type" label="Service Type"> </el-table-column>
      <el-table-column prop="date" label="Appointment Date"> </el-table-column>
      <el-table-column prop="time" label="Appointment Time"> </el-table-column>
      <el-table-column prop="notes" label="Notes"> </el-table-column>
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
        service_type: "",
        date: "",
        time: '',
        notes: "",
      }],
    }
  },
  methods:{
    async getAllAppointment(){
      const {data:res} = await this.$http.get("allAppointment");
      //console.log(res);
      this.customerForm=res.data
    },
  },
  created(){
    this.getAllAppointment()
  }
};
</script>

<style scoped>

</style>