<script setup lang="ts">
import type { SidebarProps } from "@/components/ui/sidebar"

import {
    Store,
    ShoppingBag,
    ClipboardClock,
    Sparkles,
} from "lucide-vue-next"

import {
    Sidebar,
    SidebarContent,
    SidebarFooter,
    SidebarHeader,
    SidebarMenu,
    SidebarMenuItem,
    SidebarMenuButton,
    SidebarRail,
} from "@/components/ui/sidebar"

import NavUser from "@/components/NavUser.vue"

const props = withDefaults(defineProps<SidebarProps>(), {
    collapsible: "icon",
})

// This is sample data.
const data = {
    navMenu: [
        {
            title: "Shop",
            name: "shop",
            icon: Store,
            isActive: true,
        },
        {
            title: "Bag",
            name: "bag",
            icon: ShoppingBag,
            isActive: false,
        },
        {
            title: "Order",
            name: "order",
            icon: ClipboardClock,
            isActive: false,
        },
    ],
}
</script>

<template>
    <Sidebar v-bind="props">
        <SidebarHeader>
            <SidebarMenu>
                <SidebarMenuItem>
                    <SidebarMenuButton size="lg">
                        <div
                            class="flex aspect-square size-8 items-center justify-center rounded-md bg-sidebar-primary text-sidebar-primary-foreground">
                            <Sparkles class="size-4" />
                        </div>
                        <div class="flex flex-col gap-0.5 leading-none">
                            <span class="font-semibold">Shop</span>
                            <span class="text-sm">everything</span>
                        </div>
                    </SidebarMenuButton>
                </SidebarMenuItem>
            </SidebarMenu>
        </SidebarHeader>
        <SidebarContent class="p-2">
            <SidebarMenu>
                <SidebarMenuItem v-for="menu in data.navMenu" :key="menu.title">
                    <SidebarMenuButton :tooltip="menu.title" @click.prevent="$router.push({ name: menu.name })">
                        <component :is="menu.icon" v-if="menu.icon" />
                        <span>{{ menu.title }}</span>
                    </SidebarMenuButton>
                </SidebarMenuItem>
            </SidebarMenu>
        </SidebarContent>
        <SidebarFooter>
            <NavUser/>
        </SidebarFooter>
        <SidebarRail />
    </Sidebar>
</template>
