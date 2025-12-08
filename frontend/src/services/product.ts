import { useUserStore } from "@/stores/user"

const PRODUCT_API_URL = "http://localhost:8080/products"
const CART_API_URL = "http://localhost:8080/carts"

export const getProducts = async () => {
    return await fetch(PRODUCT_API_URL, {
        credentials: "include"
    }).then(async (response) => {
        return await response.json();
    })
}

export const getCart = async () => {
    const user = useUserStore();

    return await fetch(`${CART_API_URL}/${user.user.cartId}`, {
        credentials: "include"
    }).then(async (response) => {
        return await response.json();
    })
}

export const addToBag = async (item: object) => {
    const user = useUserStore();

    return await fetch(`${CART_API_URL}/${user.user.cartId}/items`, {
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
