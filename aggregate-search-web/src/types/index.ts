// 定义响应数据的通用结构
export interface ApiResponse<T = any> {
    code: number;
    msg: string;
    data: T;
}

// 搜索参数类型
export interface SearchParams {
    keyword?: string;
    category?: string;
    current?: number;
    size?: number;
}

// 搜素选项数据
export interface SearchOption {
    query: string;
    category: string;
    value: string;
    count: number;
}

// 搜索结果数据
export interface SearchResult {
    records: any[];
    total: number;
    pages: number;
    current: number;
    size: number;
}