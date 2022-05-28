import { createApp } from "vue";
import App from "./App.vue";

import axios from "axios";
import VueAxios from "vue-axios";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";

const app = createApp(App);
app.use(VueAxios, axios).use(ElementPlus);
axios.defaults.baseURL = "http://localhost:8080";
app.mount("#app");
