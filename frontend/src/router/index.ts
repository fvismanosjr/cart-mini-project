import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from '@/pages/auth/LoginPage.vue'
import RegisterPage from '@/pages/auth/RegisterPage.vue'
import { checkIfAuthenticated } from '@/services/auth'
import AppLayout from '@/pages/AppLayout.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/login",
            name: "login",
            component: LoginPage,
            meta : {
                guestOnly: true,
            }
        },
        {
            path: "/register",
            name: "register",
            component: RegisterPage,
            meta : {
                guestOnly: true,
            }
        },
        {
            path: "/shop",
            name: "shop",
            component: AppLayout,
            meta : {
                guestOnly: true,
            }
        }
    ],
})

router.beforeEach(async (to) => {
    const authResult = await checkIfAuthenticated();

    if (to.meta.guestOnly) {
        if (to.name === "login" && authResult) {
            return { name: "board" };
        } else return true;
    }

    if (!authResult) {
        return { name: "login" };
    }
})

export default router
