<script setup lang="ts">
import { ref, onBeforeMount, onMounted, onUpdated } from "vue";
import ArticlePageList from "../components/ArticlePageList.vue";
import QAPageList from "../components/QAPageList.vue";
import SearchBox from "../components/SearchBox.vue";
import MainPageList from "../components/MainPageList.vue";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();

const searchBoxRef = ref();
const listComponentRef = ref();

// 获取要渲染的列表组件
const getListComponentByCategory = (category: string | undefined) => {
  if (category === 'main') {
    return MainPageList;
  } else if (category === 'article') {
    return ArticlePageList;
  } else if (category === 'qa') {
    return QAPageList;
  } else {
    return ArticlePageList;  // 默认展示文章列表
  }
};

// 在组件加载前处理路由参数
onBeforeMount(() => {
  const { category, ...queryParams } = route.query;

  // 如果没有类别参数，设置默认值并跳转到当前路径
  if (!category) {
    router.replace({ path: '/', query: { ...queryParams, category: 'article' } });
  }
});

onMounted(() => {
  // 初始化搜索框和列表
  if (route.query.category) {
    searchBoxRef.value.initSearchBox();
    listComponentRef.value.initPageList();
    listComponentRef.value.fetch(route.query);
  }
});

onUpdated(() => {
  listComponentRef.value.fetch(route.query);
});
</script>

<template>
  <div>
    <SearchBox ref="searchBoxRef" />

    <!-- 动态渲染不同的列表组件，使用 :key 强制重新渲染 -->
    <component :is="getListComponentByCategory(route.query.category as string | undefined)" ref="listComponentRef"
      :key="route.query.category as string" />
  </div>
</template>

<style scoped></style>