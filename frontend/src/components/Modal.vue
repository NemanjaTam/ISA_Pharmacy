


<template>
<div>
    <transition name="modal">      <div>
        <div class="overlay" >
          <div class="modal">
            <h1>Modal heading</h1>
            <p>This my first modal using vue.js</p>
          </div>
        </div>
      </div>
    </transition>

  </div>
</template>



<script>
export default {
  name: "Modal",
  props:['listItem'],
  data() {
    return {
        isOpen: false
    };
  },
  methods: {
    selectOffer(purchaseOrder, offer, endTime, id) {
      var today = new Date();
      var newOffer = {
        id: offer.id,
        price: offer.price,
        provider: {
          id: offer.provider.id,
          name: offer.provider.name,
          info: offer.provider.info,
          email: offer.provider.email,
          inbox: offer.provider.inbox,
          send: offer.provider.send,
          accepted: true,
        },
      };
      var body = {
        purchaseOrderId: purchaseOrder.id,
        offer: offer,
        pharmacyId: this.getPharmacy,
      };

      if (
        moment(endTime).isBefore(moment(String(today)).format("YYYY-MM-DD")) &&
        id === this.userId
      ) {
        this.$store.dispatch("updateSelectedOffer", newOffer);
        this.$store.dispatch("updatePurchaseOrderOffer", newOffer);
      } else {
        alert("You are not allowed.");
      }
    },
  },
};
</script>

<style>

.modal {
  width: 500px;
  margin: 0px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px 3px;
  transition: all 0.2s ease-in;
  font-family: Helvetica, Arial, sans-serif;
}
.fadeIn-enter {
  opacity: 0;
}

.fadeIn-leave-active {
  opacity: 0;
  transition: all 0.2s step-end;
}

.fadeIn-enter .modal,
.fadeIn-leave-active.modal {
  transform: scale(1.1);
}
button {
  padding: 7px;
  margin-top: 10px;
  background-color: green;
  color: white;
  font-size: 1.1rem;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: #00000094;
  z-index: 999;
  transition: opacity 0.2s ease;
}

</style>