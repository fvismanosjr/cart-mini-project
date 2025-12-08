const ORDER_API_URL = "http://localhost:8080/orders"

export const saveOrder = async (item: object) => {
    return await fetch(ORDER_API_URL, {
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
