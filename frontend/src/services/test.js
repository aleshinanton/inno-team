export async function getTestData() {

    const response = await fetch('/api/application?page=0&sort=name&sort=mail,asc');
    return await response.json();
}