export async function getTestData() {

    const response = await fetch('/api/application?page=0');
    return await response.json();
}