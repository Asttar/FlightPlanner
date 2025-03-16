
<template>
  <header>
    <h1>Very good flight planner</h1>
  </header>
  <!-- The router-view is where the matched route will render -->
   <div class="content">
    <router-view />
   </div>
</template>


<script setup>
</script>

<style>

html, body, #app {
  margin: 0;
  min-height: 100%;
  height: 100%;
}

header {
  background-color: #007bff;
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.content {
  background-color: #f6f7f8;
  min-height: 100%;
  padding: 10px;
}

h1 {
  text-shadow: 4px 4px 6px rgba(0, 0, 0, 0.5);
  color: beige;
  margin: 0;
  font-size: 30px;
}
</style>
