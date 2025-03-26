<template>
  <footer class="global-footer">
    <div class="footer-content">
      <p>&copy; {{ currentYear }} Aggregate Search. All rights reserved.</p>
    </div>
  </footer>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'GlobalFooter',
  setup() {
    const currentYear = ref(new Date().getFullYear());

    return {
      currentYear,
    };
  },
});
</script>

<style scoped>
.global-footer {
  /* background-color: #f8f9fa; */
  padding: 1rem;
  text-align: center;
  font-size: 0.875rem;
  color: #6c757d;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
}
</style>