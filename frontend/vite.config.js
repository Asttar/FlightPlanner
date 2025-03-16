import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    host: '0.0.0.0', // Exposes the server to the network
    port: 8080,      // Your chosen port
  },
  json: {
    namedExports: true,
  }
})


