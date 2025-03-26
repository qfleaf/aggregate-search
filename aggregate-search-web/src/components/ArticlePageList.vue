<script lang="ts" setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import type { SearchResult, SearchParams } from '../types';
import { fetchData } from '../apis/api';

const route = useRoute();
const router = useRouter();

// 搜索条件
const keyword = ref<string>('');
const category = ref<string>('article');
const current = ref<number>(1);
const size = ref<number>(5);

// 数据状态
const fetchPageData = ref<SearchResult>({
  total: 0,
  records: [],
  size: 0,
  current: 0,
  pages: 0,
});
const dataLoading = ref<boolean>(false);

// 更新数据方法
const fetch = async (params: SearchParams) => {
  try {
    dataLoading.value = true;
    const data = await fetchData(params);
    fetchPageData.value = data;
  } finally {
    dataLoading.value = false;
  }
};

// 初始化页面数据
const initArticlePageList = () => {
  const params: SearchParams = route.query;
  keyword.value = params.keyword || '';
  category.value = params.category || 'article';
  current.value = Number(params.current) || 1;
  size.value = Number(params.size) || 5;
};

// 处理分页变化
const onPageChange = (page: number, pageSize?: number) => {
  current.value = page;
  size.value = pageSize ?? size.value;

  router.push({
    query: {
      keyword: keyword.value,
      category: category.value,
      current: current.value,
      size: size.value,
    },
  });
};

// 分页配置
const pagination = ref({
  onChange: onPageChange,
  showSizeChanger: true,
  showQuickJumper: true,
  showTotal: (total: number) => `共 ${total} 项`,
});

// 监听路由参数变化，动态更新列表
watch(() => route.query, initArticlePageList, { immediate: true });

defineExpose({ initPageList: initArticlePageList, fetch });
</script>

<template>
  <a-list item-layout="vertical" size="large" :pagination="{
    ...pagination,
    current,
    pageSize: size,
    total: fetchPageData.total,
  }" :data-source="fetchPageData.records" :loading="dataLoading">
    <template #renderItem="{ item }">
      <a-list-item :key="item.id">
        <template #extra>
          <img width="272" alt="logo" src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png" />
        </template>
        <a-list-item-meta :description="item.content">
          <template #title>
            <a :href="item.href">{{ item.title }}</a>
          </template>
        </a-list-item-meta>
        {{ item }}
      </a-list-item>
    </template>

    <template #footer>
      <div>
        <b>收集者:</b> @搴芳
      </div>
    </template>
  </a-list>
</template>

<style scoped></style>