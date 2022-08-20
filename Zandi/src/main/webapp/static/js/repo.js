document.addEventListener("DOMContentLoaded",()=>{

    const container = document.querySelector("div.container");

    container?.addEventListener("click",(e)=>{

        const span = e.target
        const target = span?.closest("div.target")

        const seq = target?.dataset.seq;
        

    	if(seq){
        	document.location.href = `${rootPath}/git/detail_repo/${seq}`
	    }
    })

})