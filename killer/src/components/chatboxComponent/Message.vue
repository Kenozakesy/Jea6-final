<template>
   <div align="left" class="shadow-sm p-3 mb-2 bg-white rounded" id="box">

      <b-container id="container">
         <b-row>
            <b-col cols="5">
               <p>{{message.from}}</p>
            </b-col>
            <b-col cols="7">
               <p>21-4-2019 16:29</p>
            </b-col>
         </b-row>
         <b-row>
            <b-col cols="12">
               <p>{{message.content}}</p>
            </b-col>
         </b-row>
      </b-container>
   </div>
</template>

<script>
export default {
  name: 'Message',
  props: [
    'message'
  ]
}
</script>

<style scoped>
   p{
      margin: 2px;
      font-size: 13px;
   }
   #box{
      margin: 0px 10px;
   }
</style>
