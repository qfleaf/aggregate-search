import type { SearchParams, SearchResult } from '../types';
import { request } from '../utils/request';

// 获取数据接口示例
export const fetchData = async (params: SearchParams) => {
    try {
        return await request.get<{ data: SearchResult }>('/api', { params });
    } catch (error) {
        console.error('获取数据失败:', error);
        throw error;
    }
};