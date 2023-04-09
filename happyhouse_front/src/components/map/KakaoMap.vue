<template>
  <div style="border-style: double; border-color: #609ac0">
    <div v-show="!this.viewMap" id="roadView"></div>
    <div v-show="this.viewMap" id="map"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
/* eslint-disable */

const aptStore = "aptStore";
const markerImageInfo = {
  할인점: {
    location: "http://localhost:8081/static/images/marker/carts_marker.png",
    imgXSize: 32,
    imgYSize: 32,
    optionX: 16,
    optionY: 16,
  },
  편의점: {
    location: "http://localhost:8081/static/images/marker/shop_marker.png",
    imgXSize: 32,
    imgYSize: 32,
    optionX: 16,
    optionY: 16,
  },
  "커피전문점/카페/다방": {
    location: "http://localhost:8081/static/images/marker/coffee_marker.png",
    imgXSize: 32,
    imgYSize: 32,
    optionX: 16,
    optionY: 16,
  },
  패스트푸드: {
    location: "http://localhost:8081/static/images/marker/fastfood_marker.png",
    imgXSize: 32,
    imgYSize: 32,
    optionX: 16,
    optionY: 16,
  },
  충전소: {
    location: "http://localhost:8081/static/images/marker/charger_marker.png",
    imgXSize: 32,
    imgYSize: 32,
    optionX: 16,
    optionY: 16,
  },
};

export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      storeMarkers: [],
      infowindow: null,
      chargingStationMarkers: [],
    };
  },
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=eba9b037cc90d73c5795649cf9bf7e16";
      document.head.appendChild(script);
    }
  },

  watch: {
    aptList: function (newAptList) {
      this.displayAptListToMarker();
    },
    storeList: function (newStoreList) {
      this.displayStoreListToMarker();
    },
    viewMap: function (newviewMap) {
      this.displayRoadview();
    },
    aptDetail: function () {
      this.moveMap();
    },
    chargingStationList: function () {
      this.displayChargingStationListToMarker();
    },
  },

  computed: {
    ...mapState(aptStore, [
      "aptList",
      "storeList",
      "viewMap",
      "aptDetail",
      "chargingStationList",
    ]),
  },

  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      this.map = new kakao.maps.Map(container, options);
    },
    displayAptListToMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = this.aptList.map(
        (aptInfo) => new kakao.maps.LatLng(aptInfo.lat, aptInfo.lng)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayStoreListToMarker() {
      if (this.storeMarkers.length > 0) {
        this.storeMarkers.forEach((marker) => marker.setMap(null));
      }

      const positions = this.storeList.map((storeInfo) => {
        return {
          latlng: new kakao.maps.LatLng(storeInfo.lat, storeInfo.lng),
          content: `<div class="p-2">${storeInfo.name}</div>`,
        };
      });

      if (positions.length > 0) {
        const storeType = this.storeList[0].type;
        const imgLocation = markerImageInfo[storeType].location;
        const imgXSize = markerImageInfo[storeType].imgXSize;
        const imgYSize = markerImageInfo[storeType].imgYSize;
        const optionX = markerImageInfo[storeType].optionX;
        const optionY = markerImageInfo[storeType].optionY;

        let imgSize = new kakao.maps.Size(imgXSize, imgYSize);
        let imgOption = { offset: new kakao.maps.Point(optionX, optionY) };

        let markerImage = new kakao.maps.MarkerImage(
          imgLocation,
          imgSize,
          imgOption
        );

        for (let i = 0; i < positions.length; i++) {
          let marker = new kakao.maps.Marker({
            map: this.map,
            position: positions[i].latlng,
            image: markerImage,
          });

          let infowindow = new kakao.maps.InfoWindow({
            content: positions[i].content,
          });

          (function (marker, infowindow, map) {
            // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
            kakao.maps.event.addListener(marker, "mouseover", function () {
              infowindow.open(map, marker);
            });

            // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
            kakao.maps.event.addListener(marker, "mouseout", function () {
              infowindow.close();
            });
          })(marker, infowindow, this.map);

          this.storeMarkers.push(marker);
        }
      }
    },
    displayChargingStationListToMarker() {
      if (this.chargingStationMarkers.length > 0) {
        this.chargingStationMarkers.forEach((marker) => marker.setMap(null));
      }

      const positions = this.chargingStationList.map((chargerInfo) => {
        return {
          latlng: new kakao.maps.LatLng(chargerInfo.lat, chargerInfo.lng),
          content: `<div class="p-2">${chargerInfo.name}</div>`,
        };
      });

      if (positions.length > 0) {
        const storeType = "충전소";
        const imgLocation = markerImageInfo[storeType].location;
        const imgXSize = markerImageInfo[storeType].imgXSize;
        const imgYSize = markerImageInfo[storeType].imgYSize;
        const optionX = markerImageInfo[storeType].optionX;
        const optionY = markerImageInfo[storeType].optionY;

        let imgSize = new kakao.maps.Size(imgXSize, imgYSize);
        let imgOption = { offset: new kakao.maps.Point(optionX, optionY) };

        let markerImage = new kakao.maps.MarkerImage(
          imgLocation,
          imgSize,
          imgOption
        );

        for (let i = 0; i < positions.length; i++) {
          let marker = new kakao.maps.Marker({
            map: this.map,
            position: positions[i].latlng,
            image: markerImage,
          });

          let infowindow = new kakao.maps.InfoWindow({
            content: positions[i].content,
          });

          (function (marker, infowindow, map) {
            // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
            kakao.maps.event.addListener(marker, "mouseover", function () {
              infowindow.open(map, marker);
            });

            // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
            kakao.maps.event.addListener(marker, "mouseout", function () {
              infowindow.close();
            });
          })(marker, infowindow, this.map);

          this.storeMarkers.push(marker);
        }
      }
    },
    displayRoadview() {
      const rvContainer = document.getElementById("roadView");
      const position = new kakao.maps.LatLng(
        this.aptDetail.lat,
        this.aptDetail.lng
      );
      // 로드뷰 객체를 생성합니다
      const roadview = new kakao.maps.Roadview(rvContainer);
      const roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });

      // 로드뷰 초기화가 완료되면
      kakao.maps.event.addListener(roadview, "init", function () {
        // 로드뷰에 특정 장소를 표시할 마커를 생성하고 로드뷰 위에 표시합니다
        let rvMarker = new kakao.maps.Marker({
          position: position,
          map: roadview,
        });
      });
    },
    moveMap() {
      if (this.aptDetail === null) return;

      const position = new kakao.maps.LatLng(
        this.aptDetail.lat,
        this.aptDetail.lng
      );

      this.map.panTo(position);
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 84.5vh;
}
#roadView {
  width: 100%;
  height: 84.5vh;
}
</style>
