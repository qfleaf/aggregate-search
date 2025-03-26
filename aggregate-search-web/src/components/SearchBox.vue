<script lang="ts" setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import type { MenuProps } from 'ant-design-vue';
import type { SearchParams } from '../types';

const route = useRoute();
const router = useRouter();

// 搜索框绑定值
const keyword = ref<string>('');

// 当前类别
const category = ref<string>('main');
const activeKeys = ref<string[]>(['main']);

// 类别导航项
const categories = ref<MenuProps['items']>([
  { key: 'main', label: '综合' },
  { key: 'article', label: '文章' },
  { key: 'qa', label: 'Q&A' },
]);

// 初始化搜索框（暴露给父组件）
const initSearchBox = () => {
  const params: SearchParams = route.query;
  keyword.value = params.keyword || '';
  category.value = params.category || 'main';
  activeKeys.value = [category.value];
};

// 监听路由变化，保持搜索框和导航同步
watch(() => route.query, initSearchBox, { immediate: true });

defineExpose({ initSearchBox });

// 执行搜索（仅在用户提交时触发）
const handleSearch = () => {
  router.push({
    path: '/',
    query: {
      keyword: keyword.value.trim(),
      category: category.value,
    },
  });
};

// 切换类别
const handleCategoryChange = (item: { key: string }) => {
  category.value = item.key;
  router.push({
    path: '/',
    query: {
      keyword: keyword.value.trim(),
      category: category.value,
    },
  });
};
</script>

<template>
  <div class="global-search-wrapper">
    <!-- 搜索框 -->
    <a-input-search v-model:value="keyword" placeholder="输入内容进行搜索……" enter-button size="large" @search="handleSearch" />

    <!-- 类别导航 -->
    <a-menu v-model:selectedKeys="activeKeys" mode="horizontal" :items="categories" @click="handleCategoryChange" />
  </div>
</template>

<style scoped>
.global-search-wrapper {
  display: flex;
  flex-direction: column;
  gap: 8px;
}
</style>