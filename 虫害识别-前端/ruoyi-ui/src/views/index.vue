<template>
  <div>
    <div class="header-container">
      <h1 class="main-title">农作物病虫害智能识别</h1>
      <p class="subtitle">基于 YOLOV5 的图片识别模型</p>
    </div>
    <div class="shell">
      <div v-for="(item, index) in items" :key="index" class="box" @click="navigateTo(item.route)">
        <img :src="item.imgSrc" alt="image" />
        <span>{{ item.title }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ImageGallery",
  data() {
    return {
      items: [
        { imgSrc: require('@/assets/images/img (1).png'), title: '玉米', route: '/corn' },
        { imgSrc: require('@/assets/images/img (2).png'), title: '小麦', route: '/wheat' },
        { imgSrc: require('@/assets/images/img (3).png'), title: '大豆', route: '/soybean' },
        // { imgSrc: require('@/assets/images/img (2).png'), title: 'Music', route: '/music' },
        { imgSrc: require('@/assets/images/img (4).png'), title: '棉花', route: '/cotton' }
      ]
    };
  },
  methods: {
    navigateTo(route) {
      this.$router.push('PestIdentify');
    }
  }
};
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}

/* 标题样式开始 */


/* 标题容器样式 */
.header-container {
  text-align: center;
  background-color: #95a4b0; /* 背景色 */
  color: #304156;              /* 字体颜色 */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin: 0px 0px 60px 0px;
}

/* 大标题样式 */
.main-title {
  font-size: 3em;
  font-weight: bold;
  margin: 0;
  letter-spacing: 2px;
}

/* 副标题样式 */
.subtitle {
  font-size: 1.2em;
  margin-top: 10px;
  font-style: italic;
  color: rgba(255, 255, 255, 0.8);
}
/* 结束 */

body {
  /* display: flex; */
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: rgb(243, 230, 241);
 
}

.shell {
  width: 60%;
  height: 400px;
  display: flex;
  margin: 0 auto; 
}

.box {
  flex: 1;
  overflow: hidden;
  transition: 0.5s;
  margin: 0 20px;
  box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.5);
  border-radius: 20px;
  border: 10px solid #fff;
  background-color: #fff;
}

.box > img {
  width: 200%;
  height: 85%;
  object-fit: cover;
  transition: 0.5s;
}

.box > span {
  font: 200 45px '优设标题黑';
  text-align: center;
  height: 15%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.box:hover {
  flex-basis: 5%;
}

.box:hover > img {
  width: 100%;
  height: 100%;
}
</style>
