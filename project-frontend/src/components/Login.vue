<template>
    <div class="login_container">
        <!-- login block -->
        <div class="login_box">
            <!-- logo -->
            <div class="avatar_box">
                <img src="../assets/logo.png" alt="" srcset="">
            </div>
            <!-- form block -->
            <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0">
                <!-- user name -->
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" placeholder="User Name"></el-input>
                </el-form-item>
                <!-- password -->
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" type="password" placeholder="Password"></el-input>
                </el-form-item>
                <!-- button -->
                <el-form-item class="btns">
                    <el-button type="primary" @click="login">Submit</el-button>
                    <el-button type="info" @click="resetLoginForm">Reset</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script>
//import Welcome from './Welcome'
export default {
    // props:{
    //     username:"",
    //     password:""
    // },
    data(){
        return{
            loginForm:{
                username:"",
                password:""   //密码不可见 type="password"
            },
            // 表单验证
            // 1. :rule=" " 绑定表单
            // 2. 添加prop名称 并为每个prop添加验证条件
            // 3. :model 双向绑定表单信息
            loginRules:{
                username:[
                    { required: true, message: 'Username is required', trigger: 'blur' }, //必填项验证
                    { min: 5, max: 12, message: '5~12 longer', trigger: 'blur' }    //长度验证
                ],
                password:[
                    { required: true, message: 'Password is required', trigger: 'blur' }, //必填项验证
                    { min: 6, max: 10, message: '6~10 longer', trigger: 'blur' }    //长度验证
                ]
            }
        }
    },
    methods:{
        resetLoginForm(){
            this.$refs.loginFormRef.resetFields();
        },
        login(){
            //验证校验规则
            this.$refs.loginFormRef.validate(async valid => {
                if(!valid) return;  //验证失败 取反，验证不通过就跳出 返回， 什么都不做
                const {data:res} = await this.$http.post("login",this.loginForm); //访问后台 传递参数
                this.$emit("loginEmit",this.loginForm.username)
                //console.log(res);
                if(res.code == "200"){
                    this.$message.success("operation success")
                    window.sessionStorage.setItem("user",JSON.stringify(res.data));   //存储user对象 
                    
                    this.$router.push({path:"/home"})
                    //console.log(res.user);
                    
                }else{
                    this.$message.error("operation failed")
                }
            })
            
        },
        // loginEmit(){
        //     this.$emit("loginEmit",this.loginForm.username)
        // }
    }
}
</script>
<style lang="less" scoped>
//  根节点样式
.login_container{
    background-color: #2b4b6b;
    // 铺满全屏
    height: 100%;
}
.login_box{
    width: 450px;
    height: 300px;
    background-color: #fff;
    border-radius: 3px;
    //绝对定位后 才能调整
    position: absolute;
    left: 50%;
    top: 50%;
    // translate方法
    transform: translate(-50%,-50%);
    .avatar_box{
        width: 130px;
        height: 130px;
        border: 1px solid #eee;
        border-radius: 50%;
        padding: 5px;
        box-shadow: 0 0 2px #ddd;
        position: absolute;
        left: 50%;
        transform: translate(-50%,-50%);
        background-color: #0ee;
        img{
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #eee;
        }
    }
}
.btns{
    display: flex;
    justify-content: flex-end;
}
.login_form{
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
}
</style>