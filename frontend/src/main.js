import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue'
import ShowCase from './components/Showcase.vue'
import TasksShowcase from './components/TasksShowcase.vue'
import DetaolInformation from './components/DetailInformation.vue'

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

app.use(router);

app.mount('#app');
