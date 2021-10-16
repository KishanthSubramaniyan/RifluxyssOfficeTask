package com.app.rifluxyssofficetask;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rifluxyssofficetask.databinding.AdapterOfficefulldetailsBinding;
import java.util.List;

/**
 * The type Office details list adapter.
 * Developed By : KishanthSubramaniyan
 * created Date : 14-Oct-2021
 */
public class OfficeDetailsListAdapter extends RecyclerView.Adapter<OfficeDetailsListAdapter.MyViewHolder> {

    /*initalize the List<OfficeDetailPojo> object*/
    private final List<OfficeDetailsPojo> mOfficeDetailsPojoList;

    /*initalize the OnOfficeDetailsListener interface*/
    private final OnOfficeDetailsListener mOnOfficeDetailsListener;

    /*initalize the OfficeDetailsPojo object */
    private OfficeDetailsPojo mofficeDetailsPojo;

    /**
     * Instantiates a new Office details list adapter.
     *
     * @param officeDetailsPojoList   the office details pojo list
     * @param onOfficeDetailsListener the on office details listener
     */
    public OfficeDetailsListAdapter(List<OfficeDetailsPojo> officeDetailsPojoList, OnOfficeDetailsListener onOfficeDetailsListener) {
        this.mOfficeDetailsPojoList = officeDetailsPojoList;
        this.mOnOfficeDetailsListener = onOfficeDetailsListener;
    }

    /**
     * Sets data.
     *
     * @param officeDetailsPojo the office details pojo
     * @param position          the position
     */
    public void setData(OfficeDetailsPojo officeDetailsPojo,int position) {
        /*store the officeDetailPojo value */
        this.mofficeDetailsPojo = officeDetailsPojo;
        /*marked clicked the chnaged the full details of the Position */
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*initialization of Adapter Databinding in Model View Viewmodel*/
        AdapterOfficefulldetailsBinding adapterOfficefulldetailsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_officefulldetails, parent, false);
        return new MyViewHolder(adapterOfficefulldetailsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        /*mOfficeDetailsPojoList get Position based on the Value binding the Adapter */
        OfficeDetailsPojo officeDetailsPojo = mOfficeDetailsPojoList.get(position);
        /*moffice detail pojo value not null cecking in this Line */
        if (mofficeDetailsPojo != null) {

            /*marked clicked based the details set in the adapter class */
            holder.mAdapterOfficefulldetailsBinding.setOfficeDetailsData(officeDetailsPojo);
            /* marker show the binding value based on the show */
            holder.mAdapterOfficefulldetailsBinding.setFullOfficeDetailsData(new ShowOfficeDetailsData(true,true));

        } else {

            /*marked clicked based the details set in the adapter class */
            holder.mAdapterOfficefulldetailsBinding.setOfficeDetailsData(officeDetailsPojo);
            /* marker show the binding value based on the show */
            holder.mAdapterOfficefulldetailsBinding.setFullOfficeDetailsData(new ShowOfficeDetailsData(false,false));

        }

        /*view Model class initalize*/
        holder.mAdapterOfficefulldetailsBinding.setOfficeAdapterViewModels(new OfficeAdapterViewModels(holder.mAdapterOfficefulldetailsBinding, position, mOnOfficeDetailsListener));

    }

    @Override
    public int getItemCount() {
        return mOfficeDetailsPojoList.size();
    }


    /**
     * The type My view holder.
     */
    static class MyViewHolder extends RecyclerView.ViewHolder  {

        private final AdapterOfficefulldetailsBinding mAdapterOfficefulldetailsBinding;

        /**
         * Instantiates a new My view holder.
         *
         * @param officefulldetailsBinding the officefulldetails binding
         */
        public MyViewHolder(@NonNull AdapterOfficefulldetailsBinding officefulldetailsBinding) {
            super(officefulldetailsBinding.getRoot());

            this.mAdapterOfficefulldetailsBinding = officefulldetailsBinding;
        }

    }

    /**
     * The type Office adapter view models.
     */
    public static class OfficeAdapterViewModels extends BaseObservable {

        private final AdapterOfficefulldetailsBinding mAdapterOfficefulldetailsBinding;

        private final int adapterPosition;

        private final OnOfficeDetailsListener onOfficeDetailsListener;

        /**
         * Instantiates a new Office adapter view models.
         *
         * @param mAdapterOfficefulldetailsBinding the m adapter officefulldetails binding
         * @param adapterPosition                  the adapter position
         * @param mOnOfficeDetailsListener         the m on office details listener
         */
        public OfficeAdapterViewModels(AdapterOfficefulldetailsBinding mAdapterOfficefulldetailsBinding, int adapterPosition, OnOfficeDetailsListener mOnOfficeDetailsListener) {
            this.mAdapterOfficefulldetailsBinding = mAdapterOfficefulldetailsBinding;
            this.adapterPosition = adapterPosition;
            this.onOfficeDetailsListener = mOnOfficeDetailsListener;
            mAdapterOfficefulldetailsBinding.setOfficeAdapterViewModels(this);
        }

        /**
         * On show full details.
         */
        public void onShowFullDetails() {

            if (mAdapterOfficefulldetailsBinding.getFullOfficeDetailsData().isShowOfficeData()) {
            mAdapterOfficefulldetailsBinding.setFullOfficeDetailsData(new ShowOfficeDetailsData(false,false)); }
            else { mAdapterOfficefulldetailsBinding.setFullOfficeDetailsData(new ShowOfficeDetailsData(true,true)); }

        }

        /**
         * On direction map.
         */
        public void onDirectionMap() { if (onOfficeDetailsListener != null) { onOfficeDetailsListener.onOfficeDetailsShow(adapterPosition,1); } }

        /**
         * On call.
         */
        public void onCall() { if (onOfficeDetailsListener != null) { onOfficeDetailsListener.onOfficeDetailsShow(adapterPosition,2); } }

        /**
         * On compose email.
         */
        public void onComposeEmail() { if (onOfficeDetailsListener != null) { onOfficeDetailsListener.onOfficeDetailsShow(adapterPosition,3); } }

        /**
         * On call website.
         */
        public void onCallWebsite() { if (onOfficeDetailsListener != null) { onOfficeDetailsListener.onOfficeDetailsShow(adapterPosition,4); } }

        /**
         * On call facebook.
         */
        public void onCallFacebook() { if (onOfficeDetailsListener != null) { onOfficeDetailsListener.onOfficeDetailsShow(adapterPosition,5); } }

    }

    /**
     * The interface On office details listener.
     */
    interface OnOfficeDetailsListener {
        /**
         * On office details show.
         *
         * @param adapterPosition the adapter position
         * @param clickPosition   the click position
         */
        void onOfficeDetailsShow(int adapterPosition, int clickPosition);
    }
}
