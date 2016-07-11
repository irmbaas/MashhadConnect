package ir.mbaas.mashhadconnect.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.models.AppMenu;
import ir.mbaas.mashhadconnect.models.AppMenus;

/**
 * Created by Mahdi on 1/22/2016.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_DIVIDER = 2;

    private AppMenus appMenus;

    private String name;
    private String avatar;
    private String email;

    public static int selected_item = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int HolderId;

        TextView textView;
        TextView iconView;
        ImageView profile;
        TextView Name;
        TextView email;

        public ViewHolder(View itemView,int ViewType) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.tv_menuText);
                iconView = (TextView) itemView.findViewById(R.id.tv_menuIcon);

                iconView.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/webhostinghub-glyphs.ttf"));
                HolderId = 1;

                itemView.setClickable(true);
                itemView.setFocusable(true);
                itemView.setFocusableInTouchMode(true);
            } else if(ViewType == TYPE_DIVIDER) {
                HolderId = 2;
            } else {
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.circleView);
                HolderId = 0;
            }
        }
    }

    public NavigationAdapter(AppMenus appMenus,String Name,String Email, String Avatar,
                             int selectedItem){
        this.appMenus = appMenus;
        name   = Name;
        email  = Email;
        avatar = Avatar;
        this.selected_item = selectedItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_item,parent,false);
            ViewHolder vhItem = new ViewHolder(v,viewType);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_header,parent,false);
            ViewHolder vhHeader = new ViewHolder(v,viewType);
            return vhHeader;
        }  else if (viewType == TYPE_DIVIDER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_divider_line,parent,false);
            ViewHolder vhDivider = new ViewHolder(v,viewType);
            return vhDivider;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder.HolderId == 1) {
            AppMenu menu = appMenus.menus.get(position - 1);
            holder.textView.setText(menu.title);
            holder.iconView.setText(menu.icon);
            holder.itemView.setSelected(position == selected_item);
        } else if(holder.HolderId == 0) {
            if(!avatar.isEmpty()) {
                Glide.with(holder.profile.getContext())
                        .load(avatar)
                        .override(70, 70)
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(holder.profile);
            } else {
                holder.profile.setImageResource(R.drawable.avatar);
            }
            holder.Name.setText(name);
            holder.email.setText(email);
        }
    }

    @Override
    public int getItemCount() {
        return appMenus.menus.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        else if(isDivider(position))
            return TYPE_DIVIDER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isDivider(int position) {
        return appMenus.menus.get(position - 1).type == AppMenus.MenuType.DIVIDER;
    }

    public void setSelectedItem(int pos) {
        selected_item = pos;
    }

    public int getSelectedItem() {
        return selected_item;
    }
}
