<template>
    <div style="width: 100%;height:65px;display: flex;flex-direction: column;
            position: sticky;z-index: 1000;top: 0px;background-color: white;" v-show="show_nav">
            <div style="width: 100%;height:65px;display: flex;justify-content: space-between;">
                <div style="width: 50%;height: 100%;display: flex;align-items: center;margin-left: 240px;">
                    <img src="../html/image/boat.png" style="width: 45px;height: 45px;">
                    <p style="margin: 20px;font-size: 20px;padding-top: 20px;">JS Association</p>
                </div>
                <div style="width: 50%;height: 100%;display: flex;align-items: center;">
                    <div style="width: 250px;height: 100%;display: flex;align-items: center;margin-top: 25px;">
                        <span class="NavigationBar">
                            <a href="" class="NavigationBarp" style="color: #000;padding-bottom: 2.5px;">活动</a>
                        </span>
                        <span class="NavigationBar">
                            <a href="" class="NavigationBarp" style="color: #000;padding-bottom: 2.5px;">公告</a>
                        </span>
                        <span class="NavigationBar">
                            <a href="" class="NavigationBarp" style="color: #000;padding-bottom: 2.5px;">招聘信息</a>
                        </span>
                        <span class="NavigationBar">
                            <a href="" class="NavigationBarp" style="color: #000;padding-bottom: 2.5px;">关于我们</a>
                        </span>
                    </div>
                    <div style="margin: 0px 0px 0px 45px;">
                        <img id="login" src="../html/image/avatar.jpg" style="width: 35px;height: 35px;" 
                            @mousemove="setList('login')">
                        <div id="loginchild" style="display: none;background-color: rgba(68, 68, 68, 0.452);
                            margin: 5px 0 0 -40px;" @mousemove="setList('login')" @mouseout="outList()">
                            <div>
                                <button class="sbutton write button_style loginbutton" @click="loginclick()">
                                    注册/登陆
                                </button>
                            </div>
                            <div>
                                <button class="sbutton write button_style loginbutton">
                                    个人中心
                                </button>
                            </div>
                            <div>
                                <button class="sbutton write button_style loginbutton">
                                    退出登陆
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div style="width: 930px;height: 1px;margin: auto;background-color: black;overflow: hidden;
            opacity: 0.2;"></div>
        </div>
</template>

<script>
module.exports = {
    data: function() {
        return {
            
        }
    }
}
</script>

