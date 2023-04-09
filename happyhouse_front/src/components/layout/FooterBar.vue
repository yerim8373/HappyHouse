<template>
  <div id="footer">
    <div class="top_footer">
      <div class="inner">
        <ul>
          <li>이용약관</li>
          <li class="bold">위치기반서비스 이용약관</li>
          <li class="bold">개인정보처리방침</li>
          <li>제휴/프로모션문의</li>
          <li>이메일주소무단수집거부</li>
          <li>파트너센터</li>
          <div class="space"></div>
        </ul>
      </div>
    </div>
    <div class="bottom_footer">
      <div class="inner">
        <div class="f_logo">
          <img src="@/assets/logo3.png" style="width: 100px; height: 40px" />
        </div>
        <div class="copyright">
          <ul>
            <li>
              <span>주소</span>
              대전광역시 유성구 덕명동 124 삼성화재연수원
            </li>
            <li>
              <span>대표자</span>
              송예림, 이우원
            </li>
            <li>
              <span>이메일</span>
              yerim8373@naver.com, lww1224@gmail.com
            </li>
          </ul>
          <p>© Musicake Company Corp.</p>
        </div>
        <div class="info">
          <ul>
            <li>
              <span>고객센터</span>
              1877-8000
            </li>
            <li>070-8100-1000 <span>(인터넷 무료전화)</span></li>
            <div class="space"></div>
          </ul>
        </div>
        <div class="space"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      scrollTimer: 0,
      scrollY: 0,
    };
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  methods: {
    handleScroll() {
      if (this.scrollTimer) {
        return;
      }
      this.scrollTimer = setTimeout(() => {
        this.scrollY = window.scrollY;
        clearInterval(this.scrollTimer);
        this.scrollTimer = 0;
      }, 100);
    },
    toTop() {
      window.scrollTo({
        top: 0,
        behavior: "smooth",
      });
    },
  },
};
</script>

<style>
#footer {
  width: 100%;
  height: 130px;
  background-color: #1e1f21;
  position: fixed;
  bottom: 0;
}

.top_footer {
  width: 100%;
  height: 73px;
  line-height: 73px;
  border-bottom: 1px solid #303133;
  font-size: 12px;
  color: #5e605d;
}

.top_footer > .inner > ul > li {
  float: left;
  color: #5e605d;
  padding-right: 32px;
}

.top_footer > .inner > ul > .bold {
  color: #fff;
}

.bottom_footer {
  padding: 20px;
  font-size: 8px;
  color: #999999;
}

.f_logo {
  float: left;
  padding-right: 54px;
}

.copyright {
  float: left;
}

.copyright > p {
  padding-top: 20px;
}

.copyright > ul > li {
  height: 20px;
}

.copyright > ul > li > span {
  padding-right: 20px;
  display: inline-block;
}

.info {
  float: right;
  font-size: 17px;
  color: #609ac0;
}

.info > ul > li {
  float: left;
}

.info > ul > li:nth-child(1) > span {
  color: #5e605b;
  font-size: 12px;
  padding-right: 5px;
}

.info > ul > li:nth-child(2) > span {
  font-size: 12px;
  padding-left: 5px;
}

.info > ul > li:nth-child(2):before {
  content: "";
  display: inline-block;
  background-color: #565955;
  width: 1px;
  height: 13px;
  margin-left: 12px;
  margin-right: 8px;
}
</style>
