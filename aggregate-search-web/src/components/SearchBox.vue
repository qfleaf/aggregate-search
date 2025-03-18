<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
    name: 'SearchBox',
    emits: ['search'],
    setup(_, { emit }) {
        const query = ref('');

        const onInput = () => {
            // Handle input event if needed
        };

        const onSearch = () => {
            emit('search', query.value);
        };

        return {
            query,
            onInput,
            onSearch,
        };
    },
});
</script>

<template>
    <div class="search-box">
        <input 
            v-model="query" 
            @input="onInput" 
            type="text" 
            placeholder="Search..." 
            class="search-input"
        />
        <button @click="onSearch" class="search-button">Search</button>
    </div>
</template>

<style scoped>
.search-box {
    display: flex;
    align-items: center;
}

.search-input {
    flex: 1;
    padding: 0.5rem;
    margin-right: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.search-button {
    padding: 0.5rem 1rem;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.search-button:hover {
    background-color: #0056b3;
}
</style>