<template>
  <div>
    Customer List
    <el-card>
      <el-row :gutter="25">

        <el-col :span="4">
        </el-col>
      </el-row>
      <el-table stripe border :data="customerForm" style="width: 100%">
        <el-table-column type="index" />
        <el-table-column
          prop="customer_firstname"
          label="Customer Firstname"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="customer_lastname"
          label="Customer Lastname"
          width="180"
        >
        </el-table-column>
          <el-table-column
          prop="phone_number"
          label="Customer Phone"
          width="280"
        >
        </el-table-column>
        <el-table-column
          prop="member_status"
          label="Member Status"
          width="180"
        >
        </el-table-column>
        <el-table-column label="Action">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showCEditDialog(scope.row.customer_id)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteCustomer(scope.row.customer_id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog
      title="Customer"
      :visible.sync="dialogVisible"
      width="30%"
      append-to-body
    >
    </el-dialog>

    <el-dialog
      title="Edit Customer"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form :model="editForm" ref="editFormRef" label-width="100px">
        <el-form-item label="FirstName" prop="customer_firstname">
          <el-input v-model="editForm.customer_firstname"></el-input>
        </el-form-item>
        <el-form-item label="LastName" prop="customer_lastname">
          <el-input v-model="editForm.customer_lastname"></el-input>
        </el-form-item>
                <el-form-item label="Phone" prop="phone_number">
          <el-input v-model="editForm.phone_number"></el-input>
        </el-form-item>
         <el-form-item label="MemberStatus" prop="member_status">
          <el-select
            v-model="editForm.member_status"
            placeholder="Choose Status"
          >
            <el-option
              label="False"
              value="False"
            ></el-option>
            <el-option
              label="True"
              value="True"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editCustomer">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      editForm: {},
      editDialogVisible: false,
      dialogVisible: false,
      customerForm: [],
    };
  },
  methods: {
    async getAllCustomer() {
      const { data: res } = await this.$http.get("allCustomer");
      this.customerForm = res.data;
    },
    async deleteCustomer(customer_id) {
      const confirmResult = await this.$confirm("Are you sure?", "warning", {
        confirmButtonText: "confirm",
        cancelButtonText: "cancel",
        type: "warning",
      }).catch((err) => err);
      if (confirmResult != "confirm") {
        return this.$message.info("canceled");
      }
      const { data: res } = await this.$http.delete(
        `deleteCustomer/${customer_id}`
      );
      if (res != "success") {
        return this.$message.error("Failed !!!");
      }
      this.$message.success("Success !!!");
      this.getAllCustomer();
    },
    async showCEditDialog(customer_id) {
      const { data: res } = await this.$http.get(`getCustomer/${customer_id}`);
      this.editForm = res.data;
      this.editDialogVisible = true;
      console.log(res.data);
    },
    async editCustomer() {
      const { data: res } = await this.$http.put("editCustomer", this.editForm);
      if (res != "success") {
        return this.$message.error("Failed !!!");
      }
      this.$message.success("Success !!!");
      this.editDialogVisible = false;
      this.getAllCustomer();
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields(); 
    },
  },
  created() {
    this.getAllCustomer();
  },
};
</script>

<style scoped>
</style>
