<script lang="ts">
export const description
    = "A sidebar that collapses to icons."
export const iframeHeight = "800px"
export const containerClass = "w-full h-full"
</script>

<script setup lang="ts">
import AppSidebar from "@/components/AppSidebar.vue"
import {
    SidebarInset,
    SidebarProvider,
    SidebarTrigger,
} from "@/components/ui/sidebar"

import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from '@/components/ui/card'

import {
    ShoppingBag
} from "lucide-vue-next"

import { Badge } from '@/components/ui/badge'
import { Button } from "@/components/ui/button"
</script>

<template>
    <SidebarProvider>
        <AppSidebar />
        <SidebarInset>
            <header
                class="flex h-16 shrink-0 items-center gap-2 transition-[width,height] ease-linear group-has-data-[collapsible=icon]/sidebar-wrapper:h-12">
                <div class="flex items-center gap-2 px-4">
                    <SidebarTrigger class="-ml-1" />
                </div>
            </header>
            <div class="flex flex-1 flex-col gap-4 p-4 pt-0">
                <div class="grid auto-rows-min gap-4 md:grid-cols-4">
                    <template v-for="x in 8" :key="x">
                        <Card class="pt-0">
                            <div class="bg-muted/50 rounded-md h-[200px] aspect-square w-full"></div>
                            <CardHeader class="flex flex-col gap-3">
                                <Badge variant="default">In Stock</Badge>
                                <div class="flex justify-between w-full">
                                    <div>
                                        <CardTitle class="mb-1">Sample Product</CardTitle>
                                        <CardDescription>Product description</CardDescription>
                                    </div>
                                    <span class="text-xl font-medium">$20.00</span>
                                </div>
                            </CardHeader>
                            <CardFooter class="flex flex-wrap gap-2">
                                <Button variant="outline" size="default">
                                    <ShoppingBag />
                                    Add to Bag
                                </Button>
                                <!-- <Button variant="destructive" size="sm">
                                    <ShoppingBag />
                                    Remove from Bag
                                </Button> -->
                            </CardFooter>
                        </Card>
                    </template>
                </div>
            </div>
        </SidebarInset>
    </SidebarProvider>
</template>
