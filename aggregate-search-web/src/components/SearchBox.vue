<template>
  <div class="global-search-wrapper" style="max-width: 100%">
    <a-auto-complete
      v-model:value="value"
      :dropdown-match-select-width="252"
      style="width: 100%"
      :options="dataSource"
      @select="onSelect"
      @search="handleSearch"
    >
      <template #option="item">
        <div style="display: flex; justify-content: space-between">
          <span>
            Found {{ item.query }} on
            <a
              :href="`https://s.taobao.com/search?q=${item.query}`"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ item.category }}
            </a>
          </span>
          <span>{{ item.count }} results</span>
        </div>
      </template>
      <a-input-search size="large" placeholder="输入内容进行搜索……" enter-button></a-input-search>
    </a-auto-complete>
    <a-menu v-model:selectedKeys="activeCategory" mode="horizontal" :items="categories" />
  </div>
</template>

<script lang="ts" setup>
import { type MenuProps } from 'ant-design-vue';
import { ref } from 'vue';

// 搜索框
interface Option {
  query: string;
  category: string;
  value: string;
  count: number;
}
const value = ref('');
const dataSource = ref<Option[]>([]);
const onSelect = (value: string) => {
  console.log('onSelect', value);
};

const getRandomInt = (max: number, min = 0) => {
  return Math.floor(Math.random() * (max - min + 1)) + min;
};

const searchResult = (query: string): Option[] => {
  return new Array(getRandomInt(5))
    .join('.')
    .split('.')
    .map((_item, idx) => ({
      query,
      category: `${query}${idx}`,
      value: `${query}${idx}`,
      count: getRandomInt(200, 100),
    }));
};
const handleSearch = (val: string) => {
  dataSource.value = val ? searchResult(val) : [];
};

// 类别导航
const activeCategory = ref<string[]>(['main'])
const categories = ref<MenuProps['items']>([
    {
    key: 'main',
    label: '综合',
  },
  {
    key: 'article',
    label: '文章',
  },
  {
    key: 'qa',
    label: 'Q&A',
  },
])
</script>