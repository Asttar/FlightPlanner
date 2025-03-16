import { createRouter, createWebHistory } from 'vue-router'
import MainPage from "../components/MainPage.vue";
import Flight from '../components/Flight.vue';

const routes = [
    {
        path: "/",
        name: "MainPage",
        component: MainPage,
    },
    {
        path: "/flight/:id",
        name: "Flight",
        component: Flight,
    },
    {
        path: "/:pathMatch(.*)*",
        redirect: "/",
    },

]

const router = createRouter({
    history: createWebHistory('/'),
    routes
})

export default router