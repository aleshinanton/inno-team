import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue'
import ShowCase from './components/Showcase.vue'
import TasksShowcase from './components/TasksShowcase.vue'
import DetaolInformation from './components/DetailInformation.vue'
import { $axios } from '@baloise/vue-axios'
import { vueKeycloak } from '@baloise/vue-keycloak'
import { getToken } from '@baloise/vue-keycloak'


const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/', redirect: '/projects' },
      { path: '/projects', component: ShowCase },
      { path: '/tasks', component: TasksShowcase },
      { path: '/projects/:projectId', component: DetaolInformation, props: true },
    ],
    linkActiveClass: 'active'
});

const app = createApp(App);

const axiosApiInstance = $axios.create()
axiosApiInstance.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

// Request interceptor for API calls
axiosApiInstance.interceptors.request.use(
    async config => {
        const token = await getToken()
        config.headers = {
            Authorization: `Bearer ${token}`,
        }
        return config
    },
    error => {
        Promise.reject(error)
    },
)

app.use(router)

app.use(vueKeycloak, {
    initOptions: {
        flow: 'standard', // default
        checkLoginIframe: false, // default
        onLoad: 'login-required', // default
    },
    config: {
        url: process.env.VUE_APP_KEYCLOAK_URL,
        realm: 'inno_team',
        clientId: 'inno_team_client',
        "enable-cors": true
    }
})

app.mount('#app');
