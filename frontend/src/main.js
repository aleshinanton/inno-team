import {createApp} from 'vue'
import { vueKeycloak } from '@baloise/vue-keycloak'
import App from './App.vue'
import { $axios } from '@baloise/vue-axios'
import { getToken } from '@baloise/vue-keycloak'

const app = createApp(App)

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

app.mount('#app')
