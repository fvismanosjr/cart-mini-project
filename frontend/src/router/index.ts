import { createRouter, createWebHistory } from 'vue-router'
import { checkIfAuthenticated } from '@/services/auth'
import LoginForm from '@/components/LoginForm.vue'
import RegisterForm from '@/components/RegisterForm.vue'
import AppLayout from '@/pages/AppLayout.vue'
import AuthLayout from '@/pages/AuthLayout.vue'
import ShopPage from '@/pages/ShopPage.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/login",
            name: "login",
            component: LoginForm,
            meta: {
                guestOnly: true,
                layout: AuthLayout
            }
        },
        {
            path: "/register",
            name: "register",
            component: RegisterForm,
            meta: {
                guestOnly: true,
                layout: AuthLayout
            }
        },
        {
            path: "/shop",
            name: "shop",
            component: ShopPage,
            meta: {
                layout: AppLayout
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
