package com.example.gocart.ui.home.adapters

//class productDetailAdapter (val context: Context, val productsClickListener: ProductsClickListener) : RecyclerView.Adapter<productDetailAdapter.ProductsViewHolder>() {
//
//    private val productsList: ArrayList<Products> = ArrayList()
//
//    fun addList(brandsList: ArrayList<Products>) {
//        this.productsList.clear()
//        this.productsList.addAll(brandsList)
//        notifyDataSetChanged()
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ProductsViewHolder(
//        ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//    )
//
//    override fun getItemCount(): Int {
//        return productsList.size
//    }
//
//    @SuppressLint("CheckResult")
//    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
//        val productsModel = productsList[position]
//
//        holder.apply {
//
//            tvTitle.text = productsModel.title
//
//            Glide.with(context).apply {
//                load(productsModel.image!!.src).into(ivProduct)
//            }
//
//            itemView.setOnClickListener {
//                productsClickListener.onProductClickListener(productsModel, position)
//            }
//
//        }
//    }
//
//    class ProductsViewHolder(binding: ProductItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        val ivProduct = binding.productImage
//        val tvTitle = binding.productName
//    }
//
//    interface ProductsClickListener {
//        fun onProductClickListener(collection: Products, position: Int)
//    }
//
//}