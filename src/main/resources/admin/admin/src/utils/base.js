const base = {
    get() {
        return {
            url : "http://localhost:8080/springbooti406t5sg/",
            name: "springbooti406t5sg",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbooti406t5sg/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "起点树艺术培训机构系统"
        } 
    }
}
export default base
