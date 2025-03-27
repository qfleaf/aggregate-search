<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import type { SearchParams, SearchResult } from "../types";
import { fetchData } from "../apis/api";

// 路由和路由器实例
const route = useRoute();
const router = useRouter();

// 搜索条件
const keyword = ref<string>('');
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

// 更新数据方法（只请求综合数据）
const fetch = async (params: SearchParams) => {
    try {
        dataLoading.value = true;

        // 仅请求类别为 main 的综合数据
        const response = await fetchData({ ...params, category: "main" });

        // 更新数据
        fetchPageData.value = {
            total: response.total,
            records: response.records,
            size: params.size || 5,
            current: params.current || 1,
            pages: response.pages,
        };
    } catch (error) {
        console.error("数据加载失败", error);
    } finally {
        dataLoading.value = false;
    }
};

// 初始化页面数据
const initPageList = () => {
    const params: SearchParams = route.query;
    keyword.value = params.keyword || '';
    current.value = Number(params.current) || 1;
    size.value = Number(params.size) || 5;
    fetch(params);
};

// 处理分页变化
const onPageChange = (page: number, pageSize?: number) => {
    current.value = page;
    size.value = pageSize ?? size.value;

    router.push({
        query: {
            keyword: keyword.value,
            category: "main",
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

onMounted(() => {
    initPageList();
});

// 监听路由参数变化，动态更新列表
watch(() => route.query, initPageList, { immediate: true });

// 向父组件暴露方法
defineExpose({ initPageList, fetch });
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
                    <img width="272" alt="logo"
                        src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png" />
                </template>

                <a-list-item-meta :description="item.content">
                    <template #title>
                        <a :href="item.href">{{ item.title }}</a>
                    </template>
                </a-list-item-meta>

                <!-- 根据类型展示不同标签 -->
                <span v-if="item.category === 'article'" style="color: #1890ff;">[文章]</span>
                <span v-if="item.category === 'qa'" style="color: #52c41a;">[问答]</span>

                <p>{{ item.summary }}</p>

                <!-- 显示创建时间 -->
                <p>创建时间: {{ formatDate(item.createdTime) }}</p>
            </a-list-item>
        </template>

        <template #footer>
            <div>
                <b>收集者:</b> @搴芳
            </div>
        </template>
    </a-list>
</template>

<script lang="ts">
// 格式化时间为 YYYY-MM-DD hh:mm:ss 形式
const formatDate = (date: string) => {
    const d = new Date(date);
    return `${d.getFullYear()}-${(d.getMonth() + 1).toString().padStart(2, '0')}-${d.getDate().toString().padStart(2, '0')} ${d.getHours().toString().padStart(2, '0')}:${d.getMinutes().toString().padStart(2, '0')}:${d.getSeconds().toString().padStart(2, '0')}`;
};
</script>

<style scoped></style>