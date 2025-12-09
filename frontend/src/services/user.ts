import { useUserStore } from "@/stores/user"

const USER_API_URL = "http://localhost:8080/users"

export const findUser = async (userId: number) => {
    return await fetch(`${USER_API_URL}/${userId}`, {
        credentials: "include"
    }).then(async (response) => {
        return await response.json()
    });
}

export const addToBag = async (item: object) => {
    const user = useUserStore();

    return await fetch(`${USER_API_URL}/${user.user.id}/carts/${user.user.cartId}/items`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(item),
        credentials: "include"
    }).then(async (response) => {
        return await response.json();
    })
}

export const updateBag = async (item: { id: number, quantity: number }) => {
    const user = useUserStore();

    return await fetch(`${USER_API_URL}/${user.user.id}/carts/${user.user.cartId}/items/${item.id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(item),
        credentials: "include"
    }).then(async (response) => {
        return await response.json();
    })
}

export const removeFromBag = async (cartItemId: number) => {
    const user = useUserStore();

    return await fetch(`${USER_API_URL}/${user.user.id}/carts/${user.user.cartId}/items/${cartItemId}`, {
        method: "DELETE",
        credentials: "include"
    });
}

export const saveOrder = async (item: object) => {
    const user = useUserStore();

    return await fetch(`${USER_API_URL}/${user.user.id}/orders`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(item),
        credentials: "include"
    }).then(async (response) => {
        return await response.json();
    })
}

export const cancelOrder = async (orderId: number, item: object) => {
    const user = useUserStore();

    return await fetch(`${USER_API_URL}/${user.user.id}/orders/${orderId}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(item),
        credentials: "include"
    }).then(async (response) => {
        return await response.json();
    })
}
