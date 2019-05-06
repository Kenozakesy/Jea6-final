<template>
   <div id="box">
      <div class="shadow-sm p-3 mb-2 bg-white rounded">
         <b-container id="container">
            <b-row>
               <b-col cols="4">
                  <img src="../../assets/discord-512.png" width="50" height="50">
               </b-col>
               <b-col cols="8">
                  <p id="name">name friend</p>
               </b-col>
            </b-row>
         </b-container>
      </div>
   </div>
</template>

<script>
export default {
  name: 'Friend'
}
</script>

<style scoped>
   p{
      margin: 2px;
      font-size: 11px;
   }
   #name{
      margin-top: 15px;
   }
   #box{
      margin: 0px 10px;
   }
</style>
