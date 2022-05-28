const router = new VueRouter({
    mode: "hash",
    base: process.env.BASE_URL,
    routes
})

const routes = [
{
    name:'/',
    path:'',
    redirect:''
}]

export default router