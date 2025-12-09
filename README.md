# cart-mini-project

A minimal shopping-cart demo application with a Kotlin/Gradle backend and a Vue 3 + Vite frontend.

This repository contains a small full-stack example that demonstrates product listing, user authentication, a shopping bag (cart), and order listing. The frontend is implemented with Vue 3 + TypeScript and the backend is organized under `backend/` and built with Gradle/Kotlin.

## Tech stack

- Frontend: Vue 3, Vite, TypeScript, pnpm (see `frontend/`)
- Backend: Kotlin + Gradle (see `backend/`)
- Local persistence: frontend uses a small local-storage persistence plugin for the user; the backend contains configuration in `src/main/resources`.

## Key features

- Product browsing and listing
- User authentication (login/register flows)
- Shopping bag (cart) operations
- Order listing / simple order flow

## Repository layout

- `backend/` — Kotlin/Gradle backend source, configuration and build files
- `frontend/` — Vue 3 + Vite frontend application (TypeScript)
- `insert-products.sql` — sample SQL for seeding product data

## Local development

1. Backend

	- Change into the backend folder and run the Gradle wrapper. If this is a Spring Boot app, the wrapper will start the server (default port 8080 unless configured otherwise in `application.properties`).

	```bash
	cd backend
	./gradlew bootRun
	```

2. Frontend

	- Use pnpm to install deps and run the dev server (Vite default: port 5173).

	```bash
	cd frontend
	pnpm install
	pnpm dev
	```

3. Notes

	- Backend and frontend communicate over HTTP; adjust `application.properties` or the frontend API base URL in `frontend/src/services` if you run servers on non-default ports.
	- The frontend contains a persistence plugin `frontend/src/plugins/persistToLocalStorage.ts` used to keep user state locally.

## Build for production

- Backend: `cd backend && ./gradlew build`
- Frontend: `cd frontend && pnpm build`

## Contributing

Feel free to open issues or pull requests. For substantial changes, please add a short description of the change and any migration steps required.
