<script setup lang="ts">
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from '@/components/ui/table'

import {
    Empty,
    EmptyContent,
    EmptyDescription,
    EmptyHeader,
    EmptyMedia,
    EmptyTitle,
} from '@/components/ui/empty'

import { Badge } from '@/components/ui/badge'
import { Button } from '@/components/ui/button'
import { ClipboardClock, Store, X } from 'lucide-vue-next'
import { formatToDateTime, canBeCanceled } from '@/helpers/Number'
import { ref } from 'vue'
import { cancelOrder, findUser } from '@/services/user'
import { useUserStore } from '@/stores/user'
import type { OrderResponseType } from '@/lib/types'

const user = useUserStore();
const orders = ref<OrderResponseType[]>([]);

const emit = defineEmits<{
    (e: "update:list", value: boolean): void,
}>();

findUser(user.user.id).then((response) => {
    orders.value = response.orders;
})

const cancelOrderEvent = (orderId: number) => {
    cancelOrder(orderId, {
        status: "canceled"
    }).then(() => {
        emit("update:list", true);
    })
}
</script>

<template>
    <div v-if="orders.length">
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead class="w-[150px]">Order</TableHead>
                    <TableHead>Item Count</TableHead>
                    <TableHead>Amount</TableHead>
                    <TableHead>Date Ordered</TableHead>
                    <TableHead>Status</TableHead>
                    <TableHead class="w-[100px]"></TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                <TableRow v-for="order in orders" :key="order.id">
                    <TableCell>{{ order.id }}</TableCell>
                    <TableCell>{{ order.items.length }}</TableCell>
                    <TableCell>${{ order.totalAmount }}</TableCell>
                    <TableHead>{{ formatToDateTime(order.createdAt) }}</TableHead>
                    <TableCell>
                        <Badge variant="default">{{ order.status }}</Badge>
                    </TableCell>
                    <TableCell class="text-right">
                        <Button
                            v-if="order.status.toLowerCase() != 'canceled' && canBeCanceled(order.createdAt)"
                            variant="secondary"
                            size="icon-sm"
                            @click.prevent="cancelOrderEvent(order.id)"
                        >
                            <X />
                        </Button>
                    </TableCell>
                </TableRow>
            </TableBody>
        </Table>
    </div>
    <Empty v-else>
        <EmptyHeader>
            <EmptyMedia variant="icon">
                <ClipboardClock />
            </EmptyMedia>
        </EmptyHeader>
        <EmptyTitle>Your Order History Is Empty</EmptyTitle>
        <EmptyDescription>Looks like you haven’t ordered anything from us yet.</EmptyDescription>
        <EmptyContent>
            <Button variant="secondary" @click.prevent="$router.push({ name: 'shop' })">
                <Store />
                Start Shopping
            </Button>
        </EmptyContent>
    </Empty>
</template>
