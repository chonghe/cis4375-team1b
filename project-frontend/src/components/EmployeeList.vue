<template>
  <div>
    <el-table
      stripe
      :data="employeeForm"
      style="width: 100%"
    >
      <el-table-column type="index" />
      <el-table-column prop="employee_id" label="Employee ID" width="180"> </el-table-column>
      <el-table-column prop="employee_firstname" label="Employee Firstname" width="180"> </el-table-column>
      <el-table-column prop="employee_lastname" label="Employee Lastname"> </el-table-column>
      <el-table-column prop="username" label="Employee Username"> </el-table-column>
      <el-table-column prop="birthday" label="Employee Date of Birth"> </el-table-column>
      
    </el-table>
    <div>Form for Updating employee info</div>
    <select v-model="employeeForm">
    @foreach($employes as $employee)
    </select>

    <span>Selected: {{employeeForm }}</span>
    
  </div>
  
</template>

<script>
export default {
  data(){
    return{
      employeeForm: [{
        employee_id: "",
        employee_firstname: "",
        employee_lastname: "",
        username: "",
        birthday: "",
      }],
    }
  },
  methods:{
    async getAllEmployee(){
      const {data:res} = await this.$http.get("allEmployee");
      this.employeeForm=res.data
    },
  },
  created(){
    this.getAllEmployee()
  }
};
</script>

<style scoped>

</style>