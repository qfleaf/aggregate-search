import { createApp } from 'vue'
import router from './router'
import './style.css'
import App from './AggregateSearch.vue'

createApp(App)
    .use(router)
    .mount('#app')
