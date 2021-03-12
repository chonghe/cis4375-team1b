<template>
  <div>
    User List
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">Home</el-breadcrumb-item>
      <el-breadcrumb-item>Authorization manage</el-breadcrumb-item>
      <el-breadcrumb-item>User manage</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 用户列表主题部分 -->
    <el-card>
      <el-row :gutter="25">
        <!-- 搜索区域 -->
        <el-col :span="10">
          <el-input
            placeholder="input content"
            v-model="queryInfo.query"
            clearable
            @clear="getUserList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true"
            >Add User</el-button
          >
        </el-col>
      </el-row>
      <!-- 用户表格 border边框 stripe隔行变色-->
      <el-table :data="userList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="User Name" prop="username"></el-table-column>
        <el-table-column label="Email" prop="email"></el-table-column>
        <el-table-column label="Password" prop="password"></el-table-column>
        <el-table-column label="Role" prop="role"></el-table-column>
        <el-table-column label="Status" prop="status">
          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <!-- {{scope.row}} 每一行封存的数据-->
            <el-switch
              v-model="scope.row.state"
              @change="userStateChanged(scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="Option">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.id)"
            ></el-button>
            <!-- 删除 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteUser(scope.row.id)"
            ></el-button>
            <!-- 权限 -->
            <!-- 文字提示 enterable 隐藏 -->
            <el-tooltip
              effect="dark"
              content="Authorization"
              placement="top-start"
              :enterable="false"
            >
              <!-- <el-button
                type="warning"
                icon="el-icon-setting"
                size="mini"
              ></el-button> -->
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页组件 size-change 每页最大变化书 current-change 当前最大变化 layout功能组件-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 100]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-card>

    <!-- 新增用户区域 -->
    <el-dialog title="Add User" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
        <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
            <!-- 用户名 -->
            <el-form-item label="User Name" prop="username">
                <el-input v-model="addForm.username"></el-input>
            </el-form-item>
            <!-- 密码 -->
            <el-form-item label="Password" prop="password">
                <el-input v-model="addForm.password"></el-input>
            </el-form-item>
            <!-- 邮箱 -->
            <el-form-item label="Email" prop="email">
                <el-input v-model="addForm.email"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible=false">Cancel</el-button>
            <el-button type="primary" @click="addUser">Confirm</el-button>
        </span>
    </el-dialog>

    <!-- 修改用户区域 -->
    <el-dialog title="Edit User" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
        <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
            <!-- 用户名 -->
            <el-form-item label="User Name" prop="username">
                <el-input v-model="editForm.username" disabled></el-input>
            </el-form-item>
            <!-- 密码 -->
            <el-form-item label="Password" prop="password">
                <el-input v-model="editForm.password"></el-input>
            </el-form-item>
            <!-- 邮箱 -->
            <el-form-item label="Email" prop="email">
                <el-input v-model="editForm.email"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible=false">Cancel</el-button>
            <el-button type="primary" @click="editUser">Confirm</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
    created(){
        this.getUserList();
    },
    data(){
        return{
            //查询信息实体
            queryInfo:{
                query:"",   //查询信息
                pageNum: 1, //当前页
                pageSize: 5,    //每页最大数
            },
            userList:[],    //用户列表
            total:0,    //总记录数
            addDialogVisible: false, //对话框状态
            //添加表单信息
            addForm: {
                username:'',
                password:'',
                email:'',
            },
            //修改用户信息
            editForm:{},
            editDialogVisible:false,
            //添加表单验证
            addFormRules:{
                username:[
                    { required: true, message: 'Username is required', trigger: 'blur' }, //必填项验证
                    { min: 5, max: 12, message: '5~12 longer', trigger: 'blur' }    //长度验证
                ],
                password:[
                    { required: true, message: 'Password is required', trigger: 'blur' }, //必填项验证
                    { min: 6, max: 10, message: '6~10 longer', trigger: 'blur' }    //长度验证
                ],
                email:[
                    { required: true, message: 'what is email address', trigger: 'blur' }, //必填项验证
                    { min: 6, max: 15, message: 'please input your email', trigger: 'blur' }  
                ],
            },
            //修改表单验证
            editFormRules:{
                password:[
                    { required: true, message: 'Password is required', trigger: 'blur' }, //必填项验证
                    { min: 6, max: 10, message: '6~10 longer', trigger: 'blur' }    //长度验证
                ],
                email:[
                    { required: true, message: 'what is email address', trigger: 'blur' }, //必填项验证
                    { min: 6, max: 15, message: 'please input your email', trigger: 'blur' }  
                ],
            }
        }
    },
    methods:{
        //获取所有用户
        async getUserList(){
            const {data:res} = await this.$http.get("alluser",{params: this.queryInfo});
            this.userList = res.data;   //用户列表数据封装
            this.total = res.numbers;    //总用户数封装
        },
        //最大数
        handleSizeChange(newSize){
            this.queryInfo.pageSize = newSize;
            this.getUserList();
        },
        //pageNum的触发动作
        handleCurrentChange(newPage){
            this.queryInfo.pageNum = newPage;
            this.getUserList();
        },
        //修改用户状态
        async userStateChanged(userInfo){
            const {data:res} = await this.$http.put(`userstate?id=${userInfo.id}&state=${userInfo.state}`);
            if(res!="success"){
                userInfo.id = !userInfo.id;
                return this.$message.error("Failed !!!");
            }
            this.$message.success("success !!!")
        },
        //监听添加用户
        addDialogClosed(){
            this.$refs.addFormRef.resetFields();
        },
        addUser(){
            this.$refs.addFormRef.validate(async valid=>{
                if(!valid) return;
                const {data:res} = await this.$http.post("adduser",this.addForm);
                if(res!="success"){
                    return this.$message.error("Failed !!!");
                }
                this.$message.success("Success !!!")
                this.addDialogVisible = false;
                this.getUserList();
            })
        },
        //根据主键删除用户
        async deleteUser(id){
            const confirmResult = await this.$confirm('Are you sure?','warning',{
                confirmButtonText:'confirm',
                cancelButtonText:'cancel',
                type:'warning'
            }).catch(err => err)
            if(confirmResult != 'confirm'){ //取消
                return this.$message.info("canceled")
            }
            const {data:res} = await this.$http.delete("deleteuser?id="+id);
            if(res != "success"){
                return this.$message.error("Failed !!!")
            }
            this.$message.success("Success !!!");
            this.getUserList();
        },
        //修改操作
        //显示对话框
        async showEditDialog(id){
            const {data:res} = await this.$http.get("getupdate?id="+id)
            this.editForm = res;
            this.editDialogVisible = true;
        },
        //关闭窗口
        editDialogClosed(){
            this.$refs.editFormRef.resetFields();   //重置信息
        },
        //确认修改
        editUser(){
            this.$refs.editFormRef.validate(async valid=>{
                if(!valid) return;
                const {data:res} = await this.$http.put("edituser",this.editForm);
                if(res != "success"){
                    return this.$message.error("Failed !!!")
                }
                this.$message.success("Success !!!");
                //隐藏对话框
                this.editDialogVisible = false
                this.getUserList();
            })
        }
    }
};
</script>

<style lang="less" scoped>
// 面包屑样式
.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 17px;
}
</style>